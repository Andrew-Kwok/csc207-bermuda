package view;

import interface_adapter.loggedin_user.LoggedInState;
import interface_adapter.loggedin_user.LoggedInUserViewModel;
import interface_adapter.logout.LogoutController;

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
    JLabel title;
    JLabel homePageInfo;
    JLabel taskStats;

    final JButton check;
    final JButton create;
    final JButton logout;
    final JButton deleteAccount;

    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> jList = new JList<>(listModel);
    JScrollPane scrollPane = new JScrollPane(jList);

    /**
     * A window with a title and a JButton.
     */
    public LoggedInUserView(LoggedInUserViewModel loggedInUserViewModel, LogoutController logoutController) {
        this.loggedInViewModel = loggedInUserViewModel;
        this.logoutController = logoutController;
        this.loggedInViewModel.addPropertyChangeListener(this);

        title = new JLabel("Home Page\n");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        homePageInfo = new JLabel();
        homePageInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        taskStats = new JLabel();
        taskStats.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        check = new JButton(loggedInUserViewModel.CHECK_BUTTON_LABEL);
        buttons.add(check);

        create = new JButton(loggedInUserViewModel.CREATE_BUTTON_LABEL);
        buttons.add(create);

        logout = new JButton(LoggedInUserViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logout);

        deleteAccount = new JButton(LoggedInUserViewModel.DELETE_BUTTON_LABEL);
        buttons.add(deleteAccount);

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
                // get_task controller
                String taskSelected = jList.getSelectedValue();
                System.out.println("to check task in detail: " + taskSelected);
            }
        });

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
                // create task controller
                System.out.println("to create a new task");
            }
        });

        logout.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if(evt.getSource().equals(logout)){
                            LoggedInState loggedInState = loggedInUserViewModel.getState();
                            logoutController.execute(loggedInState.getUsername());
                            listModel.clear();
                        }
                    }
                }
        );

        deleteAccount.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(deleteAccount)){
                            // TODO
                            // delete account controller
                            System.out.println("to delete the user account");
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(homePageInfo);
        this.add(taskStats);
        this.add(scrollPane);
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

        String homePageMessage = "Welcome! %s \n[Bermuda ID:%s]"
                .formatted(state.getUsername(), state.getAccount_id());
        homePageInfo.setText(homePageMessage);
        taskStats.setText("You have %d tasks.".formatted(state.getTaskIDs().size()));

        listModel.addAll(state.getTaskInfo());

        if(!state.isLoggedIn()){
            JOptionPane.showMessageDialog(this, "%s logged out!".formatted(state.getUsername()));
        }
    }
}