package interface_adapter.project.share_project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.view_model.ViewModel;

public class ShareProjectViewModel extends ViewModel {
    private final String TITLE_LABEL = "Share Project View";
    private final String OTHER_USERNAME = "Enter other user's username here";
    private final static String CANCEL_BUTTON_LABEL = "Cancel";
    private final static String SHARE_BUTTON_LABEL = "Share";
    private ShareProjectState shareProjectState = new ShareProjectState();
    public ShareProjectViewModel() {
        super("share_project");
    }

    public void setState(ShareProjectState shareProjectState) {
        this.shareProjectState = shareProjectState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.shareProjectState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
