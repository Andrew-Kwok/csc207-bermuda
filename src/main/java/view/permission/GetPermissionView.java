package view.permission;

import interface_adapter.permission.create_permission.CreatePermissionViewModel;
import interface_adapter.permission.delete_permission.DeletePermissionController;
import interface_adapter.permission.delete_permission.DeletePermissionState;
import interface_adapter.permission.delete_permission.DeletePermissionViewModel;
import interface_adapter.permission.get_permission.GetPermissionController;
import interface_adapter.permission.get_permission.GetPermissionState;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.permission.update_permission.UpdatePermissionState;
import interface_adapter.permission.update_permission.UpdatePermissionViewModel;
import interface_adapter.user.loggedin.LoggedInViewModel;
import interface_adapter.view_model.ViewManagerModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import static constant.ViewConstant.*;

public class GetPermissionView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = GET_PERMISSION_VIEW_NAME;
    private final LoggedInViewModel loggedInUserViewModel;
    private final GetPermissionViewModel getPermissionViewModel;
    private final GetPermissionController getPermissionController;
    private final CreatePermissionViewModel createPermissionViewModel;
    private final UpdatePermissionViewModel updatePermissionViewModel;
    private final DeletePermissionViewModel deletePermissionViewModel;
    private final DeletePermissionController deletePermissionController;
    private final ViewManagerModel viewManagerModel;

    JLabel title;

    private final JButton createPermission;
    private final JButton updatePermission;
    private final JButton deletePermission;
    private final JButton goBack;

    DefaultListModel<Map<String, String>> permissionListModel = new DefaultListModel<>();
    JList<Map<String, String>> permissionList = new JList<>(permissionListModel);

    public GetPermissionView(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInUserViewModel,
                             GetPermissionViewModel getPermissionViewModel, GetPermissionController getPermissionController,
                             CreatePermissionViewModel createPermissionViewModel, UpdatePermissionViewModel updatePermissionViewModel,
                             DeletePermissionViewModel deletePermissionViewModel, DeletePermissionController deletePermissionController) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInUserViewModel = loggedInUserViewModel;
        this.getPermissionViewModel = getPermissionViewModel;
        this.getPermissionController = getPermissionController;
        this.createPermissionViewModel = createPermissionViewModel;
        this.updatePermissionViewModel = updatePermissionViewModel;
        this.deletePermissionViewModel = deletePermissionViewModel;
        this.deletePermissionController = deletePermissionController;

        this.getPermissionViewModel.addPropertyChangeListener(this);
        this.deletePermissionViewModel.addPropertyChangeListener(this);

        title = new JLabel(getPermissionViewModel.TITLE_LABEL);

        // On initialization, get the permissions.
        this.getPermissionController.execute(null);

        JPanel buttons = new JPanel();

        createPermission = new JButton(GetPermissionViewModel.CREATE_PERMISSION_BUTTON_LABEL);
        buttons.add(createPermission);

        updatePermission = new JButton(GetPermissionViewModel.UPDATE_PERMISSION_BUTTON_LABEL);
        buttons.add(updatePermission);

        deletePermission = new JButton(GetPermissionViewModel.DELETE_PERMISSION_BUTTON_LABEL);
        buttons.add(deletePermission);

        goBack = new JButton(GetPermissionViewModel.GO_BACK_BUTTON_LABEL);
        buttons.add(goBack);

        createPermission.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(createPermission)) {
                            viewManagerModel.setActiveView(createPermissionViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        updatePermission.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(updatePermission)) {
                            Map<String, String> selectedPermission = permissionList.getSelectedValue();
                            if (selectedPermission == null) {
                                JOptionPane.showMessageDialog(GetPermissionView.this, "Please select a permission to update.");
                            } else {
                                UpdatePermissionState updatePermissionState = updatePermissionViewModel.getState();
                                updatePermissionState.setPermissionId(selectedPermission.get(PERMISSION_ID));
                                updatePermissionState.setUserId(selectedPermission.get(USER_ID));
                                updatePermissionState.setProjectId(selectedPermission.get(PROJECT_ID));
                                updatePermissionState.setPermissionName(selectedPermission.get(PERMISSION_NAME));
                                updatePermissionState.setPermissionDescription(selectedPermission.get(PERMISSION_DESCRIPTION));
                                updatePermissionState.setInitial(true);
                                updatePermissionViewModel.setState(updatePermissionState);
                                updatePermissionViewModel.firePropertyChanged();

                                viewManagerModel.setActiveView(updatePermissionViewModel.getViewName());
                                viewManagerModel.firePropertyChanged();
                            }
                        }
                    }
                }
        );

        deletePermission.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(deletePermission)) {
                            Map<String, String> selectedPermission = permissionList.getSelectedValue();
                            if (selectedPermission == null) {
                                JOptionPane.showMessageDialog(GetPermissionView.this, "Please select a permission to delete.");
                            } else {
                                int result = JOptionPane.showConfirmDialog(GetPermissionView.this, String.format("Are you sure you want to delete permission \"%s\"?", selectedPermission.get(PERMISSION_NAME)));
                                if (result == JOptionPane.YES_OPTION) {
                                    deletePermissionController.execute(selectedPermission.get(PERMISSION_ID));
                                }
                            }
                        }
                    }
                }
        );

        goBack.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(goBack)) {
                            viewManagerModel.setActiveView(loggedInUserViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(new JScrollPane(permissionList));
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("getPermissionState")) {
            GetPermissionState state = (GetPermissionState) evt.getNewValue();
            if (state.getGetPermissionError() != null) {
                JOptionPane.showMessageDialog(this, state.getGetPermissionError());
            } else {
                permissionListModel.clear();
                for (Map<String, String> permission : state.getPermissions()) {
                    permissionListModel.addElement(permission);
                }
            }
        }

        if (evt.getPropertyName().equals("deletePermissionState")) {
            DeletePermissionState state = (DeletePermissionState) evt.getNewValue();
            if (state.getDeletePermissionError() != null) {
                JOptionPane.showMessageDialog(this, state.getDeletePermissionError());
            } else {
                JOptionPane.showMessageDialog(this, String.format("Permission with id \"%s\" deleted.", state.getPermissionId()));
                this.getPermissionController.execute(null);
            }
        }
    }

    public JButton getCreatePermissionButton() {
        return createPermission;
    }

    public JButton getUpdatePermissionButton() {
        return updatePermission;
    }

    public JButton getDeletePermissionButton() {
        return deletePermission;
    }

    public JButton getGoBackButton() {
        return goBack;
    }

    public JList<Map<String, String>> getPermissionList() {
        return permissionList;
    }
}
