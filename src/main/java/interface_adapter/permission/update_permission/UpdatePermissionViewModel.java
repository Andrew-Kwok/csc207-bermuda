package interface_adapter.permission.update_permission;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.UPDATE_PERMISSION_VIEW_NAME;

public class UpdatePermissionViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Update Permission";
    public static final String CREATE_PERMISSION_BUTTON_LABEL = "Update Permission";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String PERMISSION_ID_LABEL = "Permission ID";
    public static final String USER_ID_LABEL = "User ID";
    public static final String PROJECT_ID_LABEL = "Project ID";
    public static final String PERMISSION_NAME_LABEL = "Permission Name";
    public static final String PERMISSION_DESCRIPTION_LABEL = "Permission Description";

    private UpdatePermissionState state = new UpdatePermissionState();

    public UpdatePermissionViewModel() {
        super(UPDATE_PERMISSION_VIEW_NAME);
    }

    public void setState(UpdatePermissionState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("updatePermissionState", null, this.state);
    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public UpdatePermissionState getState() {
        return state;
    }
}
