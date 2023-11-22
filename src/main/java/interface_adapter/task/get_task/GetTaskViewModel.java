package interface_adapter.task.get_task;

import interface_adapter.permission.get_permission.GetPermissionState;
import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.GET_TASK_VIEW_NAME;

public class GetTaskViewModel extends ViewModel {
    public final String TITLE_LABEL = "Get Task";
    public static final String ADD_TASK_BUTTON_LABEL = "Add Task";
    public static final String GO_BACK_BUTTON_LABEL = "Go Back";
    private GetTaskState state = new GetTaskState();

    public GetTaskViewModel() {
        super(GET_TASK_VIEW_NAME);
    }

    public void setState(GetTaskState state) {
        this.state = state;
    }

    public GetTaskState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("getTaskState", null, this.state);
    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
