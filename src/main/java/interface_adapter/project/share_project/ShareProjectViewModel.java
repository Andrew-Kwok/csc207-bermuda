package interface_adapter.project.share_project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.view_model.ViewModel;

/**
 * This class is a ViewModel for ShareProjectView.
 * It contains the state, the label constants and field constant for the view.
 *
 * Inherits from ViewModel.
 */
public class ShareProjectViewModel extends ViewModel {
    public final String TITLE_LABEL = "Share Project View";
    public final String OTHER_USERNAME_FIELD = "Enter other user's username here";
    public final static String CANCEL_BUTTON_LABEL = "Cancel";
    public final static String SHARE_BUTTON_LABEL = "Share";
    private ShareProjectState shareProjectState = new ShareProjectState();

    /**
     * Inherits constructor from ViewModel initialized with viewName "share-project".
     *
     */
    public ShareProjectViewModel() {
        super("share-project");
    }

    /**
     * This method sets the state of the ViewModel.
     *
     * @param shareProjectState the state to be set
     */
    public void setState(ShareProjectState shareProjectState) {
        this.shareProjectState = shareProjectState;
    }

    /**
     * This method returns the state of the ViewModel.
     *
     * @return the state of the ViewModel
     */
    public ShareProjectState getState() {
        return this.shareProjectState;
    }


    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * This method alerts the PropertyChangeSupport that the property of the
     * ViewModel has changed.
     *
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.shareProjectState);
    }

    /**
     * Adds a PropertyChangeListener to the PropertyChangeSupport.
     *
     * @param listener the PropertyChangeListener to be added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
