package interface_adapter.user.logout;

import interface_adapter.user.loggedin_user.LoggedInState;
import interface_adapter.user.loggedin_user.LoggedInUserViewModel;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.user.upgrade_user.logout.LogoutOutputBoundary;
import use_case.user.upgrade_user.logout.LogoutOutputData;

public class LogoutPresenter implements LogoutOutputBoundary {

    private LoggedInUserViewModel loggedInUserViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(LoggedInUserViewModel loggedInUserViewModel, ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.loggedInUserViewModel = loggedInUserViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareLogoutView(LogoutOutputData logoutOutputData) {
        LoggedInState state = loggedInUserViewModel.getState();
        state.setLoggedIn(false);
        loggedInUserViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
