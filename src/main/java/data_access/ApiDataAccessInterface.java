package data_access;

import entity.Account;
import entity.Task;

import java.util.List;

public interface ApiDataAccessInterface {
    // user methods
    public Account createAccount(String accountName, String password);
    public Account getAccount(String accountID);
    public boolean deleteAccount(String accountID);
    public List<Task> getTasks(String accountID);
    // tasks methods
    public Task createTask(String accountID, String content, String description, String dueDate);
}
