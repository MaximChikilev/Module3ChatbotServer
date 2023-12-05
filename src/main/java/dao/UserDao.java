package dao;

import academy.prog.ContentInMessage;
import academy.prog.Status;
import academy.prog.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    public UserDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM users";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO users (userName, status, registrationDate, lastAppearanceDate) VALUES(?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE users SET userName=?, status=?, registrationDate=?, lastAppearanceDate=? WHERE userId=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM users WHERE userId=?";
    }

    @Override
    protected List<User> parseResultSet(ResultSet resultSet) {
        ArrayList<User> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new User(resultSet.getInt("userId"), resultSet.getString("userName"), Status.valueOf(resultSet.getString("status")), resultSet.getDate("registrationDate"), resultSet.getDate("lastAppearanceDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, User entity) {
        try {
            statement.setString(1, entity.getUserName());
            statement.setString(2, entity.getStatus().toString());
            statement.setDate(3, new java.sql.Date(entity.getRegistrationDate().getTime()));
            statement.setDate(4, new java.sql.Date(entity.getLastAppearanceDate().getTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User entity) {
        try {
            statement.setString(1, entity.getUserName());
            statement.setString(2, entity.getStatus().toString());
            statement.setDate(3, new java.sql.Date(entity.getRegistrationDate().getTime()));
            statement.setDate(4, new java.sql.Date(entity.getLastAppearanceDate().getTime()));
            statement.setInt(5,entity.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
