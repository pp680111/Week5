package com.zst.week5.q10.dao;

import com.zst.week5.q10.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.SQLException;

@SpringBootTest
public class PreparedStatementJDBCDaoTest {
    @Autowired
    private PreparedStatementJDBCDao dao;

    @Test
    public void testInsert() throws SQLException {
        User user = new User(2, "Flynn");
        dao.insert(user);

        user = dao.get(2);
        Assert.notNull(user, "Insert operate failed");
    }

    @Test
    public void testGet() throws SQLException {
        User user = dao.get(2);
        Assert.notNull(user, "Get operate failed");
        System.out.println(user);
    }

    @Test
    public void testDelete() throws SQLException {
        dao.delete(2);
        User user = dao.get(2);
        Assert.isNull(user, "Get operate failed");
    }

    @Test
    public void testUpdate() throws SQLException {
        User user = dao.get(2);
        user.setName("Isabeau");
        dao.update(user);
        user = dao.get(2);
        Assert.isTrue("Isabeau".equals(user.getName()), "Update operate failed");
    }
}
