package interface_adapter.permission.delete_permission;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.DELETE_PERMISSION_VIEW_NAME;

public class DeletePermissionViewModel extends ViewModel {
    public final String TITLE_LABEL = "Delete Permission";
    private DeletePermissionState state = new DeletePermissionState();

    public DeletePermissionViewModel() {
        super(DELETE_PERMISSION_VIEW_NAME);
    }

    public void setState(DeletePermissionState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("deletePermissionState", null, this.state);
    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public DeletePermissionState getState() {
        return state;
    }
}
