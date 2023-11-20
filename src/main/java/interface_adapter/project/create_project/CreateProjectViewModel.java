package interface_adapter.project.create_project;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.CREATE_PROJECT_VIEW_NAME;

public class CreateProjectViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Create Project";
    public static final String CREATE_PROJECT_BUTTON_LABEL = "Create Project";
    public static final String PROJECT_NAME_LABEL = "Enter project name";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private CreateProjectState createProjectState = new CreateProjectState();

    public CreateProjectViewModel() {
        super(CREATE_PROJECT_VIEW_NAME);
    }

    public void setState(CreateProjectState createProjectState) {
        this.createProjectState = createProjectState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("createProjectState", null, this.createProjectState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateProjectState getState() {
        return createProjectState;
    }
}
