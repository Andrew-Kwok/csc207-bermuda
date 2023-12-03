package interface_adapter.project.edit_project;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.EDIT_PROJECT_VIEW_NAME;

public class EditProjectViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Edit Project";
    public static final String EDIT_PROJECT_BUTTON_LABEL = "Edit Project";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String PROJECT_NAME_LABEL = "Project Name";

    private EditProjectState state = new EditProjectState();

    public EditProjectViewModel() {
        super(EDIT_PROJECT_VIEW_NAME);
    }

    public void setState(EditProjectState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("editProjectState", null, this.state);
    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public EditProjectState getState() {
        return state;
    }


}
