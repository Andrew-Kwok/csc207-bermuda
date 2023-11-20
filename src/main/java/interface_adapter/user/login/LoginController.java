package interface_adapter.user.login;

import domains.user.use_case.login.LoginInputBoundary;
import domains.user.use_case.login.LoginInputData;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
