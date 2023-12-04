package interface_adapter.project.delete_project;

import interface_adapter.view_model.ViewModel;

import java.beans.PropertyChangeSupport;

import static constant.ViewConstant.DELETE_PROJECT_VIEW_NAME;

public class DeleteProjectViewModel extends ViewModel {
    public final String TITLE_LABEL = "Delete Project";
    private DeleteProjectState state = new DeleteProjectState();

    public DeleteProjectViewModel() {super(DELETE_PROJECT_VIEW_NAME);}

    public void setState(DeleteProjectState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {support.firePropertyChange("deleteProjectState", null, this.state);}
    public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public DeleteProjectState getState() {return state;}
}