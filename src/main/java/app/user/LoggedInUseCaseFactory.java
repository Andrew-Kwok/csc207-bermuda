package app.user;

import domains.user.use_case.logout.LogoutInputBoundary;
import domains.user.use_case.logout.LogoutInteractor;
import domains.user.use_case.logout.LogoutOutputBoundary;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.user.loggedin_user.LoggedInViewModel;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.user.logout.LogoutController;
import interface_adapter.user.logout.LogoutPresenter;
import interface_adapter.view_model.ViewManagerModel;
import view.user.LoggedInView;

import javax.swing.*;
import java.io.IOException;

public class LoggedInUseCaseFactory {

    /**
     * Prevent instantiation.
     */
    private LoggedInUseCaseFactory() {
    }

    public static LoggedInView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInUserViewModel,
            GetProjectViewModel getProjectViewModel,
            GetPermissionViewModel getPermissionViewModel) {

        try {
            LogoutController logoutController = createLogoutUseCase(viewManagerModel, loginViewModel, loggedInUserViewModel);
            return new LoggedInView(viewManagerModel, loggedInUserViewModel, getProjectViewModel, getPermissionViewModel, logoutController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "LoggedIn View failed");
        }

        return null;
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

