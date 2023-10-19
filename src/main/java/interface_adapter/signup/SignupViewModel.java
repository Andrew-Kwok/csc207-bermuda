package interface_adapter.signup;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeListener;

public class SignupViewModel extends ViewModel {
    public static final String LOGIN_BUTTON_LABEL = "Log in";
    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String TITLE_LABEL = "Start your TODO NOW!";
    public static final String USERNAME_LABEL = "Username";
    public static final String PASSWORD_LABEL = "Password";
    public static final String REPEAT_PASSWORD_LABEL = "Password again";


    private SignupState state = new SignupState();

    public SignupViewModel() {
        super("sign up");
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public SignupState getState() {
        return state;
    }
}
