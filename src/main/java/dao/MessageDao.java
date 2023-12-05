package dao;

import academy.prog.Content;
import academy.prog.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao extends AbstractDao<Message>{
    public MessageDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM messages";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO messages (messageDate, fromUser, toUser, text) VALUES(?,?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE messages SET messageDate=?, fromUser=?, toUser=?, text=? WHERE messageId=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM messages WHERE massageId=?";
    }

    @Override
    protected List<Message> parseResultSet(ResultSet resultSet) {
        ContentDao contentDao = new ContentDao(connection);
        ArrayList<Message> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Message message = new Message(resultSet.getInt("messageId"),resultSet.getDate("messageDate"),
                        resultSet.getString("fromUser"),resultSet.getString("toUser"),resultSet.getString("text"));
                message.setContent(contentDao.getContentByMessageId(message.getMessageId()));
                result.add(message) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Message entity) {
        try {
            statement.setDate(1,new java.sql.Date(entity.getDate().getTime()));
            statement.setString(2,entity.getFrom());
            statement.setString(3,entity.getTo());
            statement.setString(4,entity.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Message entity) {
        try {
            statement.setDate(1,new java.sql.Date(entity.getDate().getTime()));
            statement.setString(2,entity.getFrom());
            statement.setString(3,entity.getTo());
            statement.setString(4,entity.getText());
            statement.setInt(5,entity.getMessageId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
