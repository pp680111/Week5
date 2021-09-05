package com.zst.week5.q10.dao;

public interface Sql {
    String USER_GET = "SELECT * FROM user WHERE id = :id";
    String USER_INSERT = "INSERT INTO user VALUES(:id, :name)";
    String USER_DELETE = "DELETE FROM user WHERE id = :id";
    String USER_UPDATE = "UPDATE user SET name = :name WHERE id = :id";

    String USER_GET_PREST = "SELECT * FROM user WHERE id = ?";
    String USER_INSERT_PREST = "INSERT INTO user VALUES(?, ?)";
    String USER_DELETE_PREST = "DELETE FROM user WHERE id = ?";
    String USER_UPDATE_PREST = "UPDATE user SET name = ? WHERE id = ?";
}
