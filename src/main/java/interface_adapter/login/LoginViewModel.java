package interface_adapter.login;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeListener;

public class LoginViewModel extends ViewModel {
    public LoginViewModel() {
        super("log in");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
