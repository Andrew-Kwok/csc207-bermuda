package view.user;

import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.project.get_project.GetProjectState;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.user.loggedin.LoggedInState;
import interface_adapter.user.loggedin.LoggedInViewModel;
import interface_adapter.user.logout.LogoutController;
import interface_adapter.view_model.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static constant.ViewConstant.LOGGED_IN_VIEW_NAME;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = LOGGED_IN_VIEW_NAME;
    private final LoggedInViewModel loggedInViewModel;
    private final GetProjectViewModel getProjectViewModel;
    private final GetPermissionViewModel getPermissionViewModel;
    private final LogoutController logoutController;
    private final ViewManagerModel viewManagerModel;
    JLabel username;
    private final JButton checkProject;
    private final JButton checkPermission;

    final JButton logOut;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInUserViewModel, GetProjectViewModel getProjectViewModel, GetPermissionViewModel getPermissionViewModel, LogoutController logoutController) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInUserViewModel;
        this.getProjectViewModel = getProjectViewModel;
        this.getPermissionViewModel = getPermissionViewModel;
        this.logoutController = logoutController;
        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        checkProject = new JButton(LoggedInViewModel.CHECK_PROJECT_BUTTON_LABEL);
        buttons.add(checkProject);

        checkPermission = new JButton(LoggedInViewModel.CHECK_PERMISSION_BUTTON_LABEL);
        buttons.add(checkPermission);

        logOut = new JButton(LoggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);

        checkProject.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(checkProject)) {
                            GetProjectState getProjectState = getProjectViewModel.getState();
                            LoggedInState loggedInState = loggedInUserViewModel.getState();
                            getProjectState.setUserId(loggedInState.getUser().getUserID());
                            getProjectState.setInitial(true);
                            getProjectViewModel.setState(getProjectState);
                            getProjectViewModel.firePropertyChanged();

                            viewManagerModel.setActiveView(getProjectViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        checkPermission.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(checkPermission)) {
                            viewManagerModel.setActiveView(getPermissionViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        logOut.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            LoggedInState loggedInState = loggedInUserViewModel.getState();
                            logoutController.execute(loggedInState.getUser().getUsername());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(username);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUser().getUsername());
        if (!state.isLoggedIn()) {
            JOptionPane.showMessageDialog(this, "%s logged out!".formatted(state.getUser().getUsername()));
        }
    }
}