package app.user;

import interface_adapter.user.loggedin_user.LoggedInViewModel;
import interface_adapter.user.login.LoginController;
import interface_adapter.user.login.LoginPresenter;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.user.logout.LogoutController;
import interface_adapter.user.logout.LogoutPresenter;
import interface_adapter.view_model.ViewManagerModel;
import domains.user.use_case.login.LoginInputBoundary;
import domains.user.use_case.login.LoginInteractor;
import domains.user.use_case.login.LoginOutputBoundary;
import domains.user.use_case.login.LoginUserDataAccessInterface;
import domains.user.use_case.logout.LogoutInputBoundary;
import domains.user.use_case.logout.LogoutInteractor;
import domains.user.use_case.logout.LogoutOutputBoundary;
import view.user.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInUserViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInUserViewModel, userDataAccessObject);
            return new LoginView(loginViewModel, loginController, loggedInUserViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
    private static LogoutController createLogoutUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInUserViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(loggedInUserViewModel, viewManagerModel, loginViewModel);

        LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutOutputBoundary);

        return new LogoutController(logoutInteractor);
    }
}
