package dao;

import academy.prog.Content;
import academy.prog.Message;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContentDao extends AbstractDao<Content> {
    public ContentDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT * FROM content";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO content (fileName,comment,fileInByteRepresentation) VALUES(?,?,?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE content SET fileName=?,comment=?,fileInByteRepresentation=? WHERE contentId=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM content WHERE contentId=?";
    }

    @Override
    protected List<Content> parseResultSet(ResultSet resultSet) {
        ArrayList<Content> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                result.add(new Content(resultSet.getInt("contentId"), resultSet.getString("fileName"),
                        resultSet.getString("comment"), resultSet.getBytes("fileInByteRepresentation")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Content entity) {
        try {
            statement.setString(1, entity.getFileName());
            statement.setString(2, entity.getComment());
            statement.setBlob(3, new SerialBlob(entity.getFileInByteRepresentation()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Content entity) {
        try {
            statement.setString(1, entity.getFileName());
            statement.setString(2, entity.getComment());
            statement.setBlob(3, new SerialBlob(entity.getFileInByteRepresentation()));
            statement.setInt(4, entity.getContentId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Content getContentByMessageId(int messageId) {
        Content content = null;
        try {
            PreparedStatement statement = connection.prepareStatement(getSelectContentByMessageIdQuery());
            prepareStatementForSelectContentByMessageId(statement, messageId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                content = new Content(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getBytes(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return content;
    }

    public String getSelectContentByMessageIdQuery() {
        return "SELECT * FROM Content WHERE contentId=(SELECT contentId FROM contentInMessage WHERE messageId=?)";
    }

    private void prepareStatementForSelectContentByMessageId(PreparedStatement statement, int messageId) {
        try {
            statement.setInt(1, messageId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
