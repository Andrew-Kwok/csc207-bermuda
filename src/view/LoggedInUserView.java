package view;

import interface_adapter.user.loggedin_user.LoggedInState;
import interface_adapter.user.loggedin_user.LoggedInUserViewModel;
import interface_adapter.user.logout.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static constant.ViewConstant.LOGGING_VIEW_NAME;

public class LoggedInUserView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = LOGGING_VIEW_NAME;
    private final LoggedInUserViewModel loggedInViewModel;
    private final LogoutController logoutController;
    JLabel username;

    final JButton logOut;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInUserView(LoggedInUserViewModel loggedInUserViewModel, LogoutController logoutController) {
        this.loggedInViewModel = loggedInUserViewModel;
        this.logoutController = logoutController;
        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameInfo = new JLabel("Currently logged in: ");
        username = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(LoggedInUserViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);

        logOut.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if(evt.getSource().equals(logOut)){
                            LoggedInState loggedInState = loggedInUserViewModel.getState();
                            logoutController.execute(loggedInState.getUsername());
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
        username.setText(state.getUsername());
        if(!state.isLoggedIn()){
            JOptionPane.showMessageDialog(this, "%s logged out!".formatted(state.getUsername()));
        }
    }
}