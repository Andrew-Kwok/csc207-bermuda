package interface_adapter.task.edit_task;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.EDIT_TASK_VIEW_NAME;

public class EditTaskViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Edit Task";
    public static final String EDIT_TASK_BUTTON_LABEL = "Edit Task";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String TASK_NAME_LABEL = "Task Name";
    public static final String TASK_DESCRIPTION_LABEL = "Task Description";

    private EditTaskState state = new EditTaskState();

    public EditTaskViewModel() {
        super(EDIT_TASK_VIEW_NAME);
    }

    public void setState(EditTaskState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("editTaskState", null, this.state);
    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public EditTaskState getState() {
        return state;
    }
}
