package interface_adapter.permission.get_permission;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

public class GetPermissionViewModel extends ViewModel {
    public final String TITLE_LABEL = "Get Permission";
    private GetPermissionState state = new GetPermissionState();
    public static final String CREATE_PERMISSION_BUTTON_LABEL = "Create Permission";

    public GetPermissionViewModel() {
        super("get_permission");
    }

    public void setState(GetPermissionState state) {
        this.state = state;
    }

    public GetPermissionState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("getPermissionState", null, this.state);
    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
