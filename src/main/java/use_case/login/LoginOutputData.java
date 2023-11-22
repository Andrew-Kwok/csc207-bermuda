package use_case.login;

import java.util.List;

public class LoginOutputData {

    private final String username;
    private final String accountID;
    private final List<String> taskInfo;

    public List<String> getTaskInfo() {
        return taskInfo;
    }

    public List<String> getTaskIDs() {
        return taskIDs;
    }

    private final List<String> taskIDs;

    public String getAccountID() {
        return accountID;
    }


    public LoginOutputData(String username, String accountID,
                           List<String> taskInfo, List<String> taskIDs) {
        this.username = username;
        this.accountID = accountID;
        this.taskInfo = taskInfo;
        this.taskIDs = taskIDs;
    }

    public String getUsername() {
        return username;
    }

}
