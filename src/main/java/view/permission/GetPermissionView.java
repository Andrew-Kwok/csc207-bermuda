package view.permission;

import domains.permission.entity.Permission;
import interface_adapter.permission.create_permission.CreatePermissionController;
import interface_adapter.permission.create_permission.CreatePermissionViewModel;
import interface_adapter.permission.delete_permission.DeletePermissionController;
import interface_adapter.permission.delete_permission.DeletePermissionState;
import interface_adapter.permission.delete_permission.DeletePermissionViewModel;
import interface_adapter.permission.get_permission.GetPermissionController;
import interface_adapter.permission.get_permission.GetPermissionState;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.view_model.ViewManagerModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static constant.ViewConstant.GET_PERMISSION_VIEW_NAME;

public class GetPermissionView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = GET_PERMISSION_VIEW_NAME;
    private final GetPermissionViewModel getPermissionViewModel;
    private final GetPermissionController getPermissionController;
    private final CreatePermissionViewModel createPermissionViewModel;
    private final DeletePermissionViewModel deletePermissionViewModel;
    private final DeletePermissionController deletePermissionController;
    private final ViewManagerModel viewManagerModel;

    JLabel title;

    final JButton createPermission;
    final JButton updatePermission;
    final JButton deletePermission;

    DefaultListModel<Permission> permissionListModel = new DefaultListModel<>();
    JList<Permission> permissionList = new JList<>(permissionListModel);
    public GetPermissionView(ViewManagerModel viewManagerModel, GetPermissionViewModel getPermissionViewModel, GetPermissionController getPermissionController,
                             CreatePermissionViewModel createPermissionViewModel,
                             DeletePermissionViewModel deletePermissionViewModel, DeletePermissionController deletePermissionController) {
        this.viewManagerModel = viewManagerModel;
        this.getPermissionViewModel = getPermissionViewModel;
        this.getPermissionController = getPermissionController;
        this.createPermissionViewModel = createPermissionViewModel;
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
                            JOptionPane.showConfirmDialog(GetPermissionView.this, "Not implemented yet.");
                        }
                    }
                }
        );

        deletePermission.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        if (evt.getSource().equals(deletePermission)) {
                            Permission selectedPermission = permissionList.getSelectedValue();
                            if (selectedPermission == null) {
                                JOptionPane.showMessageDialog(GetPermissionView.this, "Please select a permission to delete.");
                            } else {
                                int result = JOptionPane.showConfirmDialog(GetPermissionView.this, String.format("Are you sure you want to delete permission \"%s\"?", selectedPermission.getPermissionName()));
                                if (result == JOptionPane.YES_OPTION) {
                                    deletePermissionController.execute(selectedPermission.getPermissionID());
                                }
                            }
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
                for (Permission permission : state.getPermissions()) {
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
}
