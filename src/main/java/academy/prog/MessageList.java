package academy.prog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
	private static final MessageList msgList = new MessageList();

    private final Gson gson;
	private final List<Message> list = new LinkedList<>();
	
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
	public synchronized String getUsersList(){
		List<String> usersList = new ArrayList<>();
		for (Message message:list){
			if(!usersList.contains(message.getFrom())){
				usersList.add(message.getFrom());
			}
		}
		return gson.toJson(usersList);
	}
	public synchronized String getMessageListSize(){
		return gson.toJson(list.size());
	}
}
