package app.user;

import interface_adapter.user.loggedin_user.LoggedInUserViewModel;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.user.logout.LogoutController;
import interface_adapter.user.logout.LogoutPresenter;
import interface_adapter.view_model.ViewManagerModel;
import domains.user.use_case.logout.LogoutInputBoundary;
import domains.user.use_case.logout.LogoutInteractor;
import domains.user.use_case.logout.LogoutOutputBoundary;
import view.LoggedInUserView;

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

