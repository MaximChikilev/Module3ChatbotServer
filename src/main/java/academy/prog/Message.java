package academy.prog;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Message {
    private int messageId;
    private Date date = new Date();
    private String from;
    private String to;
    private String text;
    private Content content;

    public Message(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public Message(int messageId, Date date, String from, String to, String text) {
        this.messageId = messageId;
        this.date = date;
        this.from = from;
        this.to = to;
        this.text = text;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(this);
    }

    public static Message fromJSON(String s) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(s, Message.class);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").append(date)
                .append("Message ID: ").append(messageId)
                .append(", From: ").append(from).append(", To: ").append(to)
                .append(", Content: ").append((content==null)?"":content.getFileName())
                .append("] ").append(text)
                .toString();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Content getContent() {
        return content;
    }
}
