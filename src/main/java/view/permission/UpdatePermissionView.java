package view.permission;

import interface_adapter.permission.get_permission.GetPermissionController;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.permission.update_permission.UpdatePermissionController;
import interface_adapter.permission.update_permission.UpdatePermissionState;
import interface_adapter.permission.update_permission.UpdatePermissionViewModel;
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

import static constant.ViewConstant.UPDATE_PERMISSION_VIEW_NAME;

public class UpdatePermissionView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = UPDATE_PERMISSION_VIEW_NAME;
    private final UpdatePermissionViewModel updatePermissionViewModel;
    private final GetPermissionViewModel getPermissionViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JTextField userIdInputField = new JTextField(50);
    private final JTextField projectIdInputField = new JTextField(50);

    private final JTextField permissionNameInputField = new JTextField(50);
    private final JTextField permissionDescriptionInputField = new JTextField(50);
    private final UpdatePermissionController updatePermissionController;
    private final GetPermissionController getPermissionController;
    final JLabel permissionIdLabel;
    final JLabel userIdLabel;
    final JLabel projectIdLabel;
    private final JButton updatePermissionButton;
    private final JButton cancel;

    public UpdatePermissionView(
            UpdatePermissionController updatePermissionController, UpdatePermissionViewModel updatePermissionViewModel,
            GetPermissionController getPermissionController, GetPermissionViewModel getPermissionViewModel,
            ViewManagerModel viewManagerModel) {
        this.updatePermissionController = updatePermissionController;
        this.updatePermissionViewModel = updatePermissionViewModel;

        this.getPermissionController = getPermissionController;
        this.getPermissionViewModel = getPermissionViewModel;

        this.viewManagerModel = viewManagerModel;

        updatePermissionViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(UpdatePermissionViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel userIdPanel = new LabelTextPanel(new JLabel(UpdatePermissionViewModel.USER_ID_LABEL), userIdInputField);
        LabelTextPanel projectIdPanel = new LabelTextPanel(new JLabel(UpdatePermissionViewModel.PROJECT_ID_LABEL), projectIdInputField);
        LabelTextPanel permissionNamePanel = new LabelTextPanel(new JLabel(UpdatePermissionViewModel.PERMISSION_NAME_LABEL), permissionNameInputField);
        LabelTextPanel permissionDescriptionPanel = new LabelTextPanel(new JLabel(UpdatePermissionViewModel.PERMISSION_DESCRIPTION_LABEL), permissionDescriptionInputField);

        JPanel buttons = new JPanel();
        updatePermissionButton = new JButton(UpdatePermissionViewModel.CREATE_PERMISSION_BUTTON_LABEL);
        buttons.add(updatePermissionButton);

        cancel = new JButton(UpdatePermissionViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        updatePermissionButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(updatePermissionButton)) {
                            UpdatePermissionState updatePermissionState = updatePermissionViewModel.getState();
                            updatePermissionController.execute(
                                    updatePermissionState.getPermissionId(),
                                    updatePermissionState.getUserId(),
                                    updatePermissionState.getProjectId(),
                                    updatePermissionState.getPermissionName(),
                                    updatePermissionState.getPermissionDescription()
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
                        UpdatePermissionState updatePermissionState = updatePermissionViewModel.getState();
                        String text = userIdInputField.getText() + e.getKeyChar();
                        updatePermissionState.setUserId(text);
                        updatePermissionViewModel.setState(updatePermissionState);
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
                        UpdatePermissionState updatePermissionState = updatePermissionViewModel.getState();
                        String text = projectIdInputField.getText() + e.getKeyChar();
                        updatePermissionState.setProjectId(text);
                        updatePermissionViewModel.setState(updatePermissionState);
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
                        UpdatePermissionState updatePermissionState = updatePermissionViewModel.getState();
                        String text = permissionNameInputField.getText() + e.getKeyChar();
                        updatePermissionState.setPermissionName(text);
                        updatePermissionViewModel.setState(updatePermissionState);
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
                        UpdatePermissionState updatePermissionState = updatePermissionViewModel.getState();
                        String text = permissionDescriptionInputField.getText() + e.getKeyChar();
                        updatePermissionState.setPermissionDescription(text);
                        updatePermissionViewModel.setState(updatePermissionState);
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
                            clearInputFields();
                            viewManagerModel.setActiveView(getPermissionViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        this.permissionIdLabel = new JLabel(
                UpdatePermissionViewModel.PERMISSION_ID_LABEL + " :");
        this.userIdLabel = new JLabel(
                UpdatePermissionViewModel.USER_ID_LABEL + " :");
        this.projectIdLabel = new JLabel(
                UpdatePermissionViewModel.PROJECT_ID_LABEL + " :");


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(permissionIdLabel);
        this.add(userIdLabel);
        this.add(projectIdLabel);
//        this.add(userIdPanel);
//        this.add(projectIdPanel);
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
        UpdatePermissionState updatePermissionState = (UpdatePermissionState) evt.getNewValue();
        if (updatePermissionState.isInitial()) {
            setInitialInputFields();
            updatePermissionState.setInitial(false);
        } else if (updatePermissionState.getUpdatePermissionError() != null) {
            JOptionPane.showMessageDialog(this, updatePermissionState.getUpdatePermissionError());
        } else if (updatePermissionState.getPermissionId() != null) {
            JOptionPane.showMessageDialog(this, "Permission updated with id " + updatePermissionState.getPermissionId() + ".");
            this.getPermissionController.execute(null);
        }
    }

    public void setInitialInputFields() {
        UpdatePermissionState updatePermissionState = updatePermissionViewModel.getState();
        this.permissionIdLabel.setText(
                String.format("%s : %s",
                        UpdatePermissionViewModel.PERMISSION_ID_LABEL,
                        updatePermissionState.getPermissionId()
                )
        );

        this.userIdLabel.setText(
                String.format("%s : %s",
                        UpdatePermissionViewModel.USER_ID_LABEL,
                        updatePermissionState.getUserId()
                )
        );

        this.projectIdLabel.setText(
                String.format("%s : %s",
                        UpdatePermissionViewModel.PROJECT_ID_LABEL,
                        updatePermissionState.getProjectId()
                )
        );

        userIdInputField.setText(updatePermissionState.getUserId());
        projectIdInputField.setText(updatePermissionState.getProjectId());
        permissionNameInputField.setText(updatePermissionState.getPermissionName());
        permissionDescriptionInputField.setText(updatePermissionState.getPermissionDescription());
    }

    private void clearInputFields() {
        userIdInputField.setText("");
        projectIdInputField.setText("");
        permissionNameInputField.setText("");
        permissionDescriptionInputField.setText("");
    }
}
