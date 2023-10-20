package app;

import entity.NewUserFactory;
import entity.UserFactory;
import interface_adapter.loggedin_user.LoggedInUserViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.view_model.ViewManagerModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import view.LoggedInUserView;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LogoutUseCaseFactory {

    /** Prevent instantiation. */
    private LogoutUseCaseFactory() {}

    public static LoggedInUserView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInUserViewModel loggedInUserViewModel) {

        try {
            LogoutController logoutController = createLogoutUseCase(viewManagerModel, loginViewModel, loggedInUserViewModel);
            return new LoggedInUserView(loggedInUserViewModel, logoutController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "LoggedIn View failed");
        }

        return null;
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

