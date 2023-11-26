package interface_adapter.share_project.share_project_page;

import java.util.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.view_model.ViewModel;



public class ShareProjectPageViewModel extends ViewModel {
    public final String TITLE_LABEL = "Share Project Page";
    public final String SHARE_BUTTON_LABEL = "Share";
    public final String PROJECT_NAME_LABEL = "Project Name";
    public final String PROJECT_ID_LABEL = "Project ID";
    public final String BACK_BUTTON_LABEL = "Back";

    private ShareProjectPageState state = new ShareProjectPageState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public ShareProjectPageViewModel() { super("share-project-page"); }

    public void setState(ShareProjectPageState state) { this.state = state; }

    public ShareProjectPageState getState() { return this.state; }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
