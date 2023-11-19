package app.user;

import interface_adapter.user.loggedin_user.LoggedInViewModel;
import interface_adapter.user.login.LoginController;
import interface_adapter.user.login.LoginPresenter;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.user.signup.SignupController;
import interface_adapter.user.signup.SignupPresenter;
import interface_adapter.user.signup.SignupViewModel;
import interface_adapter.view_model.ViewManagerModel;
import domains.user.use_case.login.LoginInputBoundary;
import domains.user.use_case.login.LoginInteractor;
import domains.user.use_case.login.LoginOutputBoundary;
import domains.user.use_case.login.LoginUserDataAccessInterface;
import domains.user.use_case.signup.SignupInputBoundary;
import domains.user.use_case.signup.SignupInteractor;
import domains.user.use_case.signup.SignupOutputBoundary;
import domains.user.use_case.signup.SignupUserDataAccessInterface;
import view.user.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {
    }

    public static SignupView create(ViewManagerModel viewManagerModel,
                                    SignupViewModel signupViewModel, LoginViewModel loginViewModel, LoggedInViewModel loggedInUserViewModel,
                                    SignupUserDataAccessInterface signupUserDataAccessInterface, LoginUserDataAccessInterface loginUserDataAccessInterface) {
        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, signupUserDataAccessInterface);
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loggedInUserViewModel, loginViewModel, loginUserDataAccessInterface);
            return new SignupView(signupController, signupViewModel, loginController, loginViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                            SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                                                            SignupUserDataAccessInterface userDataAccessObject) throws IOException {
        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary);

        return new SignupController(userSignupInteractor);
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel,
                                                          LoggedInViewModel loggedInUserViewModel, LoginViewModel loginViewModel,
                                                          LoginUserDataAccessInterface loginUserDataAccessInterface) throws IOException {

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInUserViewModel,loginViewModel);

        LoginInputBoundary loginInputBoundary = new LoginInteractor(loginUserDataAccessInterface, loginOutputBoundary);

        return new LoginController(loginInputBoundary);
    }

}
