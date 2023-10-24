package entity.user;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userID;
    private final String username;
    private final String password;

    public User(String userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
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
}
