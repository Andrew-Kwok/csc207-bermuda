package interface_adapter.project.share_project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.view_model.ViewModel;

public class ShareProjectViewModel extends ViewModel {
    public final String TITLE_LABEL = "Share Project View";
    public final String OTHER_USERNAME_FIELD = "Enter other user's username here";
    public final static String CANCEL_BUTTON_LABEL = "Cancel";
    public final static String SHARE_BUTTON_LABEL = "Share";
    private ShareProjectState shareProjectState = new ShareProjectState();
    public ShareProjectViewModel() {
        super("share_project");
    }

    public void setState(ShareProjectState shareProjectState) {
        this.shareProjectState = shareProjectState;
    }

    public ShareProjectState getState() {
        return this.shareProjectState;
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
