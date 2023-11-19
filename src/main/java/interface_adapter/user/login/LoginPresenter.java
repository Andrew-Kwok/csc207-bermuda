package interface_adapter.user.login;

import interface_adapter.user.loggedin_user.LoggedInState;
import interface_adapter.user.loggedin_user.LoggedInViewModel;
import interface_adapter.view_model.ViewManagerModel;
import domains.user.use_case.login.LoginOutputBoundary;
import domains.user.use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
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
        loggedInState.setLoggedIn(true);
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(LoginOutputData error) {
        LoginState loginState = loginViewModel.getState();
        if(error.getErrorCode() == 1){
            loginState.setUsernameError(error.getUsername() + ": Account does not exist.");
        } else if (error.getErrorCode() == 2) {
            loginState.setPasswordError("Incorrect password for " + error.getUsername() + ".");
        }
        loginViewModel.firePropertyChanged();
    }
}
