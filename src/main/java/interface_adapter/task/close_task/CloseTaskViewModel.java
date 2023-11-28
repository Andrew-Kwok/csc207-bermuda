package interface_adapter.task.close_task;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.CLOSE_TASK_VIEW_NAME;

public class CloseTaskViewModel extends ViewModel {
    public final String TITLE_LABEL = "Close Task";
    private CloseTaskState state = new CloseTaskState();

    public CloseTaskViewModel() {
        super(CLOSE_TASK_VIEW_NAME);
    }

    public void setState(CloseTaskState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("closeTaskState", null, this.state);
    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CloseTaskState getState() {
        return state;
    }
}
