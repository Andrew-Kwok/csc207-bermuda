package interface_adapter.project.create_project;
import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class CreateProjectViewModel extends ViewModel{
    public static final String CREATE_PROJECT_BUTTON_LABEL = "Create Project";
    public static final String TITLE_LABEL = "Create Project View";
    public static final String PROJECT_NAME_LABEL = "Enter project name";
    public static final String DESCRIPTION_LABEL = "Enter description";
    public static final String DEADLINE_LABEL = "Enter deadline";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private CreateProjectState state = new CreateProjectState();

    public CreateProjectViewModel() {
        super("create project");
    }

    public void setState(CreateProjectState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateProjectState getState(){return state;}
}
