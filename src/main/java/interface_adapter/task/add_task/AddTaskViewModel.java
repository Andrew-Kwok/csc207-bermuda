package interface_adapter.task.add_task;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.ADD_TASK_VIEW_NAME;

public class AddTaskViewModel extends ViewModel {

    public static final String ADD_TASK_BUTTON_LABEL = "Add task";
    public static final String TITLE_LABEL = "Add Task";
    public static final String TASK_NAME_LABEL = "Enter task name";
    public static final String TASK_CONTENT_LABEL = "Enter contents of task";
    public static final String DEADLINE_LABEL = "Enter deadline";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private AddTaskState state = new AddTaskState();

    public AddTaskViewModel() {
        super(ADD_TASK_VIEW_NAME);
    }

    public void setState(AddTaskState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("addTaskState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public AddTaskState getState(){return state;}
}