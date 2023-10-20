package entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;

    private String password;
    private List<String> projects = new ArrayList<String>();
    private int userLevel = 1;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getProjects() {
        return new ArrayList<String>(this.projects);
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }
}
