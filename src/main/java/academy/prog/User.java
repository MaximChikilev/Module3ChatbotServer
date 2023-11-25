package academy.prog;

import java.util.Date;

public class User {
    private String userName;
    private Status status;
    private Date registrationDate;
    private Date lastAppearanceDate;

    public User() {
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
}
