package interface_adapter.loggedin_user;

import java.util.ArrayList;
import java.util.List;

public class LoggedInState {
    private String username = "";
    private String accountID = "";
    private List<String> taskInfo = new ArrayList<String>();
    private List<String> taskIDs = new ArrayList<String>();

    public void clearAllButUsername(){
        accountID = "";
        taskInfo = new ArrayList<String>();
        taskIDs = new ArrayList<String>();
    }

    public String getAccount_id() {
        return accountID;
    }

    public void setAccount_id(String accountID) {
        this.accountID = accountID;
    }

    private boolean loggedIn = false;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public LoggedInState(LoggedInState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public List<String> getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(List<String> taskInfo) {
        this.taskInfo = taskInfo;
    }

    public List<String> getTaskIDs() {
        return taskIDs;
    }

    public void setTaskIDs(List<String> taskIDs) {
        this.taskIDs = taskIDs;
    }
}
