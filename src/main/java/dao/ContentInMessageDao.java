package dao;

import academy.prog.Content;
import academy.prog.ContentInMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContentInMessageDao extends AbstractDao<ContentInMessage> {
    public ContentInMessageDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM contentInMessage";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO contentInMessage (contentId, messageId) VALUES(?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE contentInMessage SET contentId=? WHERE messageId=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM contentInMessage WHERE messageId=?";
    }

    @Override
    protected List<ContentInMessage> parseResultSet(ResultSet resultSet) {
        ArrayList<ContentInMessage> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new ContentInMessage(resultSet.getInt("contentId"), resultSet.getInt("messageId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, ContentInMessage entity) {
        try {
            statement.setInt(1, entity.getContentId());
            statement.setInt(2, entity.getMessageId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ContentInMessage entity) {
        try {
            statement.setInt(1, entity.getContentId());
            statement.setInt(2, entity.getMessageId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
