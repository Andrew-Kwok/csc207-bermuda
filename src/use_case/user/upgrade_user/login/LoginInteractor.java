package use_case.user.upgrade_user.login;

import entity.user.User;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            LoginOutputData loginOutputData = new LoginOutputData(username, 1);
            loginPresenter.prepareFailView(loginOutputData);
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                LoginOutputData loginOutputData = new LoginOutputData(username, 2);
                loginPresenter.prepareFailView(loginOutputData);
            } else {
                User user = userDataAccessObject.get(loginInputData.getUsername());
                LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), 0);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}