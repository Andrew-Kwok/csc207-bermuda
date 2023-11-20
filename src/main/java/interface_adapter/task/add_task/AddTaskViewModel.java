package interface_adapter.task.add_task;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddTaskViewModel extends ViewModel {

    public static final String ADD_TASK_BUTTON_LABEL = "Add task";
    public static final String TITLE_LABEL = "Add Task";
    public static final String TASK_NAME_LABEL = "Enter task name";
    public static final String TASK_CONTENT_LABEL = "Enter contents of task";
    public static final String DESCRIPTION_LABEL = "Enter description";
    public static final String DEADLINE_LABEL = "Enter deadline";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private AddTaskState state = new AddTaskState();

    public AddTaskViewModel() {
        super("add task");
    }

    public void setState(AddTaskState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AddTaskState getState() {
        return state;
    }
}