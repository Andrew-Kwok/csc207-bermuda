package interface_adapter.task.get_task;

import interface_adapter.permission.get_permission.GetPermissionState;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.GET_TASK_VIEW_NAME;

public class GetTaskViewModel {
    public final String TITLE_LABEL = "Get Task";
    private GetTaskState state = new GetTaskState();
    public static final String GET_TASK_BUTTON_LABEL = "Get Task";

    public GetTaskViewModel() {
        super();
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
