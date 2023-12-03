package interface_adapter.project.get_project;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.GET_PROJECT_VIEW_NAME;

public class GetProjectViewModel extends ViewModel{
    public final String TITLE_LABEL = "Get Project";
    private GetProjectState state = new GetProjectState();
    public static final String CREATE_PROJECT_BUTTON_LABEL = "Create Project";
    public static final String EDIT_PROJECT_BUTTON_LABEL = "Edit Project";
    public static final String CHECK_TASK_BUTTON_LABEL = "Check Task";
    public static final String SHARE_PROJECT_BUTTON_LABEL = "Share Project";
    public static final String DELETE_PROJECT_BUTTON_LABEL = "Delete Project";
    public static final String GO_BACK_BUTTON_LABEL = "Go Back";

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
