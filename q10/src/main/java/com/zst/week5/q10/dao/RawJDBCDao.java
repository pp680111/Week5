package com.zst.week5.q10.dao;

import com.zst.week5.q10.config.DBConfig;
import com.zst.week5.q10.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class RawJDBCDao {
    private static final Logger logger = LoggerFactory.getLogger(RawJDBCDao.class);

    @Autowired
    private DBConfig dbConfig;

    @PostConstruct
    public void postConstruct() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public User get(int id) throws SQLException {
        try {
            List<User> user = executeQuery(Sql.USER_GET.replace(":id", Integer.toString(id)), rs -> {
                try {
                    return new User(rs.getInt("id"), rs.getString("name"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }, true);

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

        String sql = Sql.USER_INSERT.replace(":id", Integer.toString(entity.getId()))
                .replace(":name", String.format("'%s'", entity.getName()));
        if (executeUpdate(sql) == 0) {
            throw new SQLException("Insert failed");
        }
    }

    public void delete(int id) throws SQLException {
        String sql = Sql.USER_DELETE.replace(":id", Integer.toString(id));
        executeUpdate(sql);
    }

    public void update(User entity) throws SQLException {
        if (entity == null) {
            return;
        }

        String sql = Sql.USER_UPDATE.replace(":id", Integer.toString(entity.getId()))
                .replace(":name", String.format("'%s'", entity.getName()));
        if (executeUpdate(sql) == 0) {
            throw new SQLException("Update row not exists");
        }
    }

    private <T> List<T> executeQuery(String sql, Function<ResultSet, T> mapper, boolean unique) throws SQLException {
        try(Connection conn = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            List<T> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(mapper.apply(rs));
                if (unique) {
                    break;
                }
            }
            return resultList;
        } catch (SQLException e) {
            throw e;
        }
    }

    private int executeUpdate(String sql) throws SQLException {
        try(Connection conn = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUsername(), dbConfig.getPassword());
            Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw e;
        }
    }
}
