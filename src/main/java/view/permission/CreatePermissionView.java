package view.permission;

import interface_adapter.permission.create_permission.CreatePermissionController;
import interface_adapter.permission.create_permission.CreatePermissionState;
import interface_adapter.permission.create_permission.CreatePermissionViewModel;
import interface_adapter.permission.get_permission.GetPermissionController;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.common.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static constant.ViewConstant.CREATE_PERMISSION_VIEW_NAME;

public class CreatePermissionView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = CREATE_PERMISSION_VIEW_NAME;
    private final CreatePermissionViewModel createPermissionViewModel;
    private final GetPermissionViewModel getPermissionViewModel;

    private final ViewManagerModel viewManagerModel;
    private final JTextField userIdInputField = new JTextField(50);
    private final JTextField projectIdInputField = new JTextField(50);

    private final JTextField permissionNameInputField = new JTextField(50);
    private final JTextField permissionDescriptionInputField = new JTextField(50);
    private final CreatePermissionController createPermissionController;
    private final GetPermissionController getPermissionController;
    private final JButton createPermissionButton;
    private final JButton cancel;

    public CreatePermissionView(
            CreatePermissionController createPermissionController, CreatePermissionViewModel createPermissionViewModel,
            GetPermissionController getPermissionController, GetPermissionViewModel getPermissionViewModel,
            ViewManagerModel viewManagerModel) {
        this.createPermissionController = createPermissionController;
        this.createPermissionViewModel = createPermissionViewModel;

        this.getPermissionController = getPermissionController;
        this.getPermissionViewModel = getPermissionViewModel;

        this.viewManagerModel = viewManagerModel;

        createPermissionViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(CreatePermissionViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel userIdPanel = new LabelTextPanel(new JLabel(CreatePermissionViewModel.USER_ID_LABEL), userIdInputField);
        LabelTextPanel projectIdPanel = new LabelTextPanel(new JLabel(CreatePermissionViewModel.PROJECT_ID_LABEL), projectIdInputField);
        LabelTextPanel permissionNamePanel = new LabelTextPanel(new JLabel(CreatePermissionViewModel.PERMISSION_NAME_LABEL), permissionNameInputField);
        LabelTextPanel permissionDescriptionPanel = new LabelTextPanel(new JLabel(CreatePermissionViewModel.PERMISSION_DESCRIPTION_LABEL), permissionDescriptionInputField);

        JPanel buttons = new JPanel();
        createPermissionButton = new JButton(CreatePermissionViewModel.CREATE_PERMISSION_BUTTON_LABEL);
        buttons.add(createPermissionButton);

        cancel = new JButton(CreatePermissionViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        createPermissionButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(createPermissionButton)) {
                            CreatePermissionState createPermissionState = createPermissionViewModel.getState();
                            createPermissionController.execute(
                                    createPermissionState.getUserId(),
                                    createPermissionState.getProjectId(),
                                    createPermissionState.getPermissionName(),
                                    createPermissionState.getPermissionDescription()
                            );
                            clearInputFields();
                        }
                    }
                }
        );

        userIdInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreatePermissionState createPermissionState = createPermissionViewModel.getState();
                        String text = userIdInputField.getText() + e.getKeyChar();
                        createPermissionState.setUserId(text);
                        createPermissionViewModel.setState(createPermissionState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        projectIdInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreatePermissionState createPermissionState = createPermissionViewModel.getState();
                        String text = projectIdInputField.getText() + e.getKeyChar();
                        createPermissionState.setProjectId(text);
                        createPermissionViewModel.setState(createPermissionState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        permissionNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreatePermissionState createPermissionState = createPermissionViewModel.getState();
                        String text = permissionNameInputField.getText() + e.getKeyChar();
                        createPermissionState.setPermissionName(text);
                        createPermissionViewModel.setState(createPermissionState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        permissionDescriptionInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreatePermissionState createPermissionState = createPermissionViewModel.getState();
                        String text = permissionDescriptionInputField.getText() + e.getKeyChar();
                        createPermissionState.setPermissionDescription(text);
                        createPermissionViewModel.setState(createPermissionState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {
                            viewManagerModel.setActiveView(getPermissionViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(userIdPanel);
        this.add(projectIdPanel);
        this.add(permissionNamePanel);
        this.add(permissionDescriptionPanel);
        this.add(buttons);
    }

    ;

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        CreatePermissionState createPermissionState = (CreatePermissionState) evt.getNewValue();
        if (createPermissionState.getCreatePermissionError() != null) {
            JOptionPane.showMessageDialog(this, createPermissionState.getCreatePermissionError());
        } else if (createPermissionState.getPermissionId() != null) {
            // On success, switch to the get permission view.
            JOptionPane.showMessageDialog(this, "Permission created with id " + createPermissionState.getPermissionId() + ".");
            this.getPermissionController.execute(null);
        }
    }

    private void clearInputFields() {
        userIdInputField.setText("");
        projectIdInputField.setText("");
        permissionNameInputField.setText("");
        permissionDescriptionInputField.setText("");
    }
}
