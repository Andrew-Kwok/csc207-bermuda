package interface_adapter.user.logout;

import domains.user.use_case.logout.LogoutOutputBoundary;
import domains.user.use_case.logout.LogoutOutputData;
import interface_adapter.user.loggedin_user.LoggedInState;
import interface_adapter.user.loggedin_user.LoggedInViewModel;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class LogoutPresenter implements LogoutOutputBoundary {

    private LoggedInViewModel loggedInUserViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(LoggedInViewModel loggedInUserViewModel, ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
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
