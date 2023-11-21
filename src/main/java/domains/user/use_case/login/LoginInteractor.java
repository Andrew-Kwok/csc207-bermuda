package domains.user.use_case.login;

import domains.user.entity.User;

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
        User emptyUser = new User(null, username, password);

        boolean userExists = false;
        try {
            userExists = userDataAccessObject.existsByName(username);
        } catch (Exception e) {
            LoginOutputData loginOutputData = new LoginOutputData(emptyUser, 3);
            loginPresenter.prepareFailView(loginOutputData);
        }

        if (!userExists) {
            LoginOutputData loginOutputData = new LoginOutputData(emptyUser, 1);
            loginPresenter.prepareFailView(loginOutputData);
        } else {
            String pwd = null;
            try {
                pwd = userDataAccessObject.getUserByUsername(username).getPassword();
            } catch (Exception e) {
                LoginOutputData loginOutputData = new LoginOutputData(emptyUser, 3);
                loginPresenter.prepareFailView(loginOutputData);
            }

            if (!password.equals(pwd)) {
                LoginOutputData loginOutputData = new LoginOutputData(emptyUser, 2);
                loginPresenter.prepareFailView(loginOutputData);
            } else {
                try {
                    User user = userDataAccessObject.getUserByUsername(loginInputData.getUsername());
                    LoginOutputData loginOutputData = new LoginOutputData(user, 0);
                    loginPresenter.prepareSuccessView(loginOutputData);
                } catch (Exception e) {
                    LoginOutputData loginOutputData = new LoginOutputData(emptyUser, 3);
                    loginPresenter.prepareFailView(loginOutputData);
                }
            }
        }
    }
}