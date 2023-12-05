package academy.prog;

import java.util.Date;

public class User {
    private int userId;
    private String userName;
    private Status status;
    private Date registrationDate;
    private Date lastAppearanceDate;

    public User() {
    }

    public User(String userName, Status status, Date registrationDate, Date lastAppearanceDate) {
        this.userName = userName;
        this.status = status;
        this.registrationDate = registrationDate;
        this.lastAppearanceDate = lastAppearanceDate;
    }

    public User(int userId, String userName, Status status, Date registrationDate, Date lastAppearanceDate) {
        this.userId = userId;
        this.userName = userName;
        this.status = status;
        this.registrationDate = registrationDate;
        this.lastAppearanceDate = lastAppearanceDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setLastAppearanceDate(Date lastAppearanceDate) {
        this.lastAppearanceDate = lastAppearanceDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLastAppearanceDate() {
        return lastAppearanceDate;
    }
}
