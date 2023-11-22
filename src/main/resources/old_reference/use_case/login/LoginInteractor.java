package use_case.login;

import data_access.ApiDataAccessInterface;
import entity.Account;
import entity.Task;

import java.util.ArrayList;
import java.util.List;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final ApiDataAccessInterface apiDataAccessInterface;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           ApiDataAccessInterface apiDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.apiDataAccessInterface = apiDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            String errorMessage = username + ": Account does not exist.";
            loginPresenter.prepareFailView(1, errorMessage);
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                String errorMessage = "Incorrect password for " + username + " !";
                loginPresenter.prepareFailView(2, errorMessage);
            } else {
                Account account = userDataAccessObject.get(username);
                List<Task> accountTasks = apiDataAccessInterface.getTasks(account.getAccountID());
                List<String> taskInfo = new ArrayList<String>();
                List<String> taskIDs = new ArrayList<String>();
                for(Task task : accountTasks){
                    String info = "%s | DUE: %s".formatted(task.getContent(), task.getDueDate());
                    taskInfo.add(info);
                    taskIDs.add(task.getTaskID());
                }
                LoginOutputData loginOutputData = new LoginOutputData(account.getAccountName(),
                        account.getAccountID(), taskInfo, taskIDs);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}