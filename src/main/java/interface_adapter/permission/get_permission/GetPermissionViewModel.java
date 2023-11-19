package interface_adapter.permission.get_permission;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.GET_PERMISSION_VIEW_NAME;

public class GetPermissionViewModel extends ViewModel {
    public final String TITLE_LABEL = "Get Permission";
    private GetPermissionState state = new GetPermissionState();
    public static final String CREATE_PERMISSION_BUTTON_LABEL = "Create Permission";
    public static final String UPDATE_PERMISSION_BUTTON_LABEL = "Update Permission";
    public static final String DELETE_PERMISSION_BUTTON_LABEL = "Delete Permission";

    public GetPermissionViewModel() {
        super(GET_PERMISSION_VIEW_NAME);
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
