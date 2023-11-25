package academy.prog;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
    private static final MessageList msgList = new MessageList();

    private final Gson gson;
    private final List<Message> list = new LinkedList<>();
    private final Map<String, User> usersList = new HashMap<>();

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public synchronized void add(Message m) {
        list.add(m);
    }

    public synchronized String toJSON(int n, String sender) {
        if (n >= list.size()) return null;
        return gson.toJson(new JsonMessages(list, n, sender));
    }

    public synchronized Map<String,User> getUsersList() {
        return usersList;
    }

    public synchronized String getMessageListSize() {
        return gson.toJson(list.size());
    }
    public synchronized void addNewUser(User user){
        usersList.put(user.getUserName(),user);
    }
    public synchronized void changeUserStatus(String userLogin, Status status){
        usersList.get(userLogin).setStatus(status);
        usersList.get(userLogin).setLastAppearanceDate(new Date());
    }
}
