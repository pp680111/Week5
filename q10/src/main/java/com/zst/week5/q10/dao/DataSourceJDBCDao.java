package com.zst.week5.q10.dao;

import com.zst.week5.q10.config.DBConfig;
import com.zst.week5.q10.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Component
public class DataSourceJDBCDao {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceJDBCDao.class);

    @Autowired
    DataSource dataSource;

    public User get(int id) throws SQLException {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            Function<ResultSet, User> resultMapper = rs -> {
                try {
                    return new User(rs.getInt("id"), rs.getString("name"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            };
            BiConsumer<PreparedStatement, Map<String, Object>> paramSetter = (stmt, param) -> {
                try {
                    stmt.setInt(1, (Integer) param.get("id"));
                } catch (SQLException e) {
                    logger.error(e.getMessage(), e);
                }
            };
            List<User> user = executeQuery(Sql.USER_GET_PREST, resultMapper, params, paramSetter,  true);
            if (user.size() > 0) {
                return user.get(0);
            }
        } catch (SQLException e) {
            throw e;
        }
        return null;
    }

    public void insert(User entity) throws SQLException {
        if (entity == null) {
            return;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("id", entity.getId());
        params.put("name", entity.getName());
        int insertRowNum = executeUpdate(Sql.USER_INSERT_PREST, params, (stmt, param) -> {
            try {
                stmt.setInt(1, (Integer) param.get("id"));
                stmt.setString(2, (String) param.get("name"));
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        });
        if (insertRowNum == 0) {
            throw new SQLException("Insert failed");
        }
    }

    public void delete(int id) throws SQLException {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        executeUpdate(Sql.USER_DELETE_PREST, params, (stmt, param) -> {
            try {
                stmt.setInt(1, (Integer) param.get("id"));
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    public void update(User entity) throws SQLException {
        if (entity == null) {
            return;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("id", entity.getId());
        params.put("name", entity.getName());
        int updateRowNum = executeUpdate(Sql.USER_UPDATE_PREST, params, (stmt, param) -> {
            try {
                stmt.setString(1, (String) param.get("name"));
                stmt.setInt(2, (Integer) param.get("id"));
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        });
        if (updateRowNum == 0) {
            throw new SQLException("Update row not exists");
        }
    }

    private <T> List<T> executeQuery(String sql,
                                     Function<ResultSet, T> resultMapper,
                                     Map<String, Object> params,
                                     BiConsumer<PreparedStatement, Map<String, Object>> paramMapper,
                                     boolean unique) throws SQLException {
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            paramMapper.accept(stmt, params);
            ResultSet rs = stmt.executeQuery();
            List<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(resultMapper.apply(rs));
                if (unique) {
                    break;
                }
            }
            return resultList;
        } catch (SQLException e) {
            throw e;
        }
    }

    private int executeUpdate(String sql, Map<String, Object> params,
                              BiConsumer<PreparedStatement, Map<String, Object>> paramMapper) throws SQLException {
        Connection conn = getConnection();
        int resultRow = 0;
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            paramMapper.accept(stmt, params);
            resultRow = stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }

        return resultRow;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
