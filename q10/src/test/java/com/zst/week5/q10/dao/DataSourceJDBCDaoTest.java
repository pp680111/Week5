package com.zst.week5.q10.dao;

import com.zst.week5.q10.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.SQLException;

@SpringBootTest
public class DataSourceJDBCDaoTest {
    @Autowired
    private DataSourceJDBCDao dao;

    @Test
    public void testInsert() throws SQLException {
        User user = new User(3, "Walter");
        dao.insert(user);

        user = dao.get(3);
        Assert.notNull(user, "Insert operate failed");
    }

    @Test
    public void testGet() throws SQLException {
        User user = dao.get(3);
        Assert.notNull(user, "Get operate failed");
        System.out.println(user);
    }

    @Test
    public void testDelete() throws SQLException {
        dao.delete(3);
        User user = dao.get(3);
        Assert.isNull(user, "Get operate failed");
    }

    @Test
    public void testUpdate() throws SQLException {
        User user = dao.get(3);
        user.setName("Jonathan");
        dao.update(user);
        user = dao.get(3);
        Assert.isTrue("Jonathan".equals(user.getName()), "Update operate failed");
    }
}
