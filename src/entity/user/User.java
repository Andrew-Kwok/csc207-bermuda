package entity.user;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userID;
    private final String username;
    private String password;
    private int userLevel = 1;

    public User(String userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public User(String userID, String username, String password, int userLevel) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.userLevel = userLevel;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
}
