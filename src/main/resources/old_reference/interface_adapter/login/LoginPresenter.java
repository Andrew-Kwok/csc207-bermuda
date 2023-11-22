package interface_adapter.login;

import interface_adapter.loggedin_user.LoggedInState;
import interface_adapter.loggedin_user.LoggedInUserViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInUserViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInUserViewModel loggedInViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        loggedInState.setAccount_id(response.getAccountID());
        loggedInState.setTaskInfo(response.getTaskInfo());
        loggedInState.setTaskIDs(response.getTaskIDs());
        loggedInState.setLoggedIn(true);
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(int errorCode, String message) {
        LoginState loginState = loginViewModel.getState();
        if(errorCode == 1){
            loginState.setUsernameError(message);
        } else if (errorCode == 2) {
            loginState.setPasswordError(message);
        }
        loginViewModel.firePropertyChanged();
    }
}
