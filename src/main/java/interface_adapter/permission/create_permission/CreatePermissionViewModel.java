package interface_adapter.permission.create_permission;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.CREATE_PERMISSION_VIEW_NAME;

public class CreatePermissionViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Create Permission";
    public static final String CREATE_PERMISSION_BUTTON_LABEL = "Create Permission";

    public static final String USER_ID_LABEL = "User ID";
    public static final String PROJECT_ID_LABEL = "Project ID";
    public static final String PERMISSION_NAME_LABEL = "Permission Name";
    public static final String PERMISSION_DESCRIPTION_LABEL = "Permission Description";

    private CreatePermissionState state = new CreatePermissionState();

    public CreatePermissionViewModel() {
        super(CREATE_PERMISSION_VIEW_NAME);
    }

    public void setState(CreatePermissionState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("createPermissionState", null, this.state);
    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreatePermissionState getState() {
        return state;
    }
}
