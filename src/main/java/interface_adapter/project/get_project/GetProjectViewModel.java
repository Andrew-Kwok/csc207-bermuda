package interface_adapter.project.get_project;

import interface_adapter.permission.get_permission.GetPermissionState;
import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.GET_PROJECT_VIEW_NAME;

public class GetProjectViewModel extends ViewModel{
    public final String TITLE_LABEL = "Get Project";
    private GetProjectState state = new GetProjectState();
    public static final String CREATE_PROJECT_BUTTON_LABEL = "Create Project";
    public static final String CHECK_PROJECT_BUTTON_LABEL = "Check Project";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public GetProjectViewModel() {
        super(GET_PROJECT_VIEW_NAME);
    }

    public void setState(GetProjectState state) {
        this.state = state;
    }

    public GetProjectState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("getProjectState", null, this.state);
    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
