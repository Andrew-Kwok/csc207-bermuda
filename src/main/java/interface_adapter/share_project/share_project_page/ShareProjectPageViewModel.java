package interface_adapter.share_project.share_project_page;

import java.util.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import interface_adapter.view_model.ViewModel;
import constant.ViewConstant;
public class ShareProjectPageViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Share Project Page";
    public static final String SHARE_BUTTON_LABEL = "Share";
    public static final String PROJECT_NAME_LABEL = "Project Name";
    public static final String PROJECT_ID_LABEL = "Project ID";
    public static final String BACK_BUTTON_LABEL = "Back";

    private ShareProjectPageState state = new ShareProjectPageState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public ShareProjectPageViewModel() { super(ViewConstant.SHARE_PROJECT_PAGE_VIEW_NAME); }

    public void setState(ShareProjectPageState newState) { this.state = newState; }

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
