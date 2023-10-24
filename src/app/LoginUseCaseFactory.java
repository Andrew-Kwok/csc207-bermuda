package app;

import entity.user.NewUserFactory;
import entity.user.UserFactory;
import interface_adapter.user.loggedin_user.LoggedInUserViewModel;
import interface_adapter.user.login.LoginController;
import interface_adapter.user.login.LoginPresenter;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.user.logout.LogoutController;
import interface_adapter.user.logout.LogoutPresenter;
import interface_adapter.view_model.ViewManagerModel;
import use_case.user.upgrade_user.login.LoginInputBoundary;
import use_case.user.upgrade_user.login.LoginInteractor;
import use_case.user.upgrade_user.login.LoginOutputBoundary;
import use_case.user.upgrade_user.login.LoginUserDataAccessInterface;
import use_case.user.upgrade_user.logout.LogoutInputBoundary;
import use_case.user.upgrade_user.logout.LogoutInteractor;
import use_case.user.upgrade_user.logout.LogoutOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInUserViewModel loggedInUserViewModel,
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
            LoggedInUserViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        UserFactory userFactory = new NewUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
    private static LogoutController createLogoutUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInUserViewModel loggedInUserViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(loggedInUserViewModel, viewManagerModel, loginViewModel);

        LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutOutputBoundary);

        return new LogoutController(logoutInteractor);
    }
}
