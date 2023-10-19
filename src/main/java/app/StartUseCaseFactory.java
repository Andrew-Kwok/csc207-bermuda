package app;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import view.StartView;

public class StartUseCaseFactory {
    private StartUseCaseFactory() {}

    public static StartView create(){
        SignupController signupController = new SignupController();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginController loginController = new LoginController();
        LoginViewModel loginViewModel = new LoginViewModel();

        return new StartView(signupController,signupViewModel, loginController, loginViewModel);

    }

}
