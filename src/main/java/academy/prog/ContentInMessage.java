package academy.prog;

public class ContentInMessage {
    private int contentId;
    private int messageId;

    public ContentInMessage(int contentId, int messageId) {
        this.contentId = contentId;
        this.messageId = messageId;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
