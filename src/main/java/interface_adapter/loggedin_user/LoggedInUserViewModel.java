package interface_adapter.loggedin_user;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeListener;

public class LoggedInUserViewModel extends ViewModel {
    public LoggedInUserViewModel() {
        super("Logged in");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
