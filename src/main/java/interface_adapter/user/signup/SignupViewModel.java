package interface_adapter.user.signup;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.SIGNUP_VIEW_NAME;

;

public class SignupViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Welcome to Bermuda!";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private SignupState state = new SignupState();

    public SignupViewModel() {
        super(SIGNUP_VIEW_NAME);
    }

    public void setState(SignupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupState getState() {
        return state;
    }
}
