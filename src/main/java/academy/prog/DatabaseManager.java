package academy.prog;

import dao.ContentDao;
import dao.ContentInMessageDao;
import dao.MessageDao;
import dao.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "Password";
    private Connection connection;
    private ContentDao contentDao;
    private MessageDao messageDao;
    private ContentInMessageDao contentInMessageDao;
    private UserDao userDao;

    public DatabaseManager() {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            contentDao = new ContentDao(connection);
            messageDao = new MessageDao(connection);
            contentInMessageDao = new ContentInMessageDao(connection);
            userDao = new UserDao(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ContentDao getContentDao() {
        return contentDao;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public ContentInMessageDao getContentInMessageDao() {
        return contentInMessageDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void initDatabase() throws SQLException {
        Statement statement = connection.createStatement();
        try {

            statement.execute("CREATE TABLE IF NOT EXISTS Content(" +
                    "contentId INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "fileName VARCHAR(30) NOT NULL," +
                    "comment VARCHAR(200) NOT NULL," +
                    "fileInByteRepresentation BLOB NOT NULL" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS Messages(" +
                    "messageId INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "messageDate DATE NOT NULL," +
                    "fromUser VARCHAR(20) NOT NULL," +
                    "toUser VARCHAR(20) NOT NULL," +
                    "text VARCHAR(200) NOT NULL" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS ContentInMessage(" +
                    "contentId INT NOT NULL," +
                    "messageId INT NOT NULL" +
                    ")");
            statement.execute("CREATE TABLE IF NOT EXISTS Users(" +
                    "userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "userName VARCHAR(50) NOT NULL," +
                    "status VARCHAR(7) NOT NULL," +
                    "registrationDate DATE NOT NULL," +
                    "lastAppearanceDate DATE NOT NULL" +
                    ")");

        } finally {
            statement.close();
        }
    }

    public List<Message> loadAllMessages() {
        return messageDao.getAll();
    }

    public Connection getConnection() {
        return connection;
    }
}
