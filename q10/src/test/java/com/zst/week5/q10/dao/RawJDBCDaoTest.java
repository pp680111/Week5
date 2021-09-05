package com.zst.week5.q10.dao;

import com.zst.week5.q10.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.sql.SQLException;

@SpringBootTest
public class RawJDBCDaoTest {
    @Autowired
    private RawJDBCDao rawJDBCDao;

    @Test
    public void testRawJDBCOperate() throws SQLException {
        User user = new User(1, "Nameless");
        rawJDBCDao.insert(user);

        User user1 = rawJDBCDao.get(1);
        Assert.notNull(user1, "get operate failed");

        user1.setName("Nanashi");
        rawJDBCDao.update(user1);
        User user2 = rawJDBCDao.get(1);
        Assert.isTrue("Nanashi".equals(user2.getName()), "Update operate failed");

        rawJDBCDao.delete(1);
        Assert.isNull(rawJDBCDao.get(1), "Delete operate failed");
    }
}
