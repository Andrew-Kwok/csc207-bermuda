package interface_adapter.task.get_task;

import interface_adapter.permission.get_permission.GetPermissionState;
import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.GET_TASK_VIEW_NAME;

public class GetTaskViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Get Task";
    public static final String PROJECTID_NAME_LABEL = "Enter project ID";
    private GetTaskState state = new GetTaskState();
    public static final String GET_TASK_BUTTON_LABEL = "Get Task";
    public GetTaskViewModel(String viewName){
        super(GET_TASK_VIEW_NAME);
    }

    public GetTaskState getState(){
        return state;
    }

    public void setState(GetTaskState state){
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("getTaskState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
