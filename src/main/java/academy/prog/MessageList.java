package academy.prog;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
    private static final MessageList msgList = new MessageList();
    private int massageId = 1;
    private DatabaseManager databaseManager;
    private final Gson gson;
    private final List<Message> list;
    private final Map<String, User> usersList = new HashMap<>();

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        databaseManager = new DatabaseManager();
        try {
            databaseManager.initDatabase();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        list = databaseManager.loadAllMessages();
        massageId = (list.size()==0)?1:list.size();
    }

    public synchronized void add(Message m) {
        m.setMessageId(massageId);
        databaseManager.getMessageDao().create(m);
        if (m.getContent() != null) {
            addContentToDB(m);
        }
        list.add(m);
        massageId++;
    }

    private synchronized void addContentToDB(Message m) {
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement("SELECT count(*) AS total FROM content");
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            m.getContent().setContentId(rs.getInt("total")+1);
            databaseManager.getContentDao().create(m.getContent());
            ContentInMessage contentInMessage = new ContentInMessage(m.getContent().getContentId(), m.getMessageId());
            databaseManager.getContentInMessageDao().create(contentInMessage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized String toJSON(int n, String sender) {
        if (n >= list.size()) return null;
        return gson.toJson(new JsonMessages(list, n, sender));
    }

    public synchronized Map<String, User> getUsersList() {
        return usersList;
    }

    public synchronized String getMessageListSize() {
        return gson.toJson(list.size());
    }

    public synchronized void addNewUser(User user) {
        databaseManager.getUserDao().create(user);
        user.setUserId(usersList.size() + 1);
        usersList.put(user.getUserName(), user);
    }

    public synchronized void changeUserStatus(String userLogin, Status status) {
        usersList.get(userLogin).setStatus(status);
        usersList.get(userLogin).setLastAppearanceDate(new Date());
        databaseManager.getUserDao().update(usersList.get(userLogin));
    }

    public String getJsonMassageById(int id) {
        return gson.toJson(list.get(id - 1));
    }
}
