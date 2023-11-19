package view.permission;

import domains.permission.entity.Permission;
import interface_adapter.permission.create_permission.CreatePermissionController;
import interface_adapter.permission.create_permission.CreatePermissionViewModel;
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
    private final CreatePermissionController createPermissionController;
    private final ViewManagerModel viewManagerModel;

    JLabel title;

    final JButton createPermission;

    DefaultListModel<Permission> permissionListModel = new DefaultListModel<>();

    public GetPermissionView(ViewManagerModel viewManagerModel, GetPermissionViewModel getPermissionViewModel, GetPermissionController getPermissionController,
                             CreatePermissionViewModel createPermissionViewModel, CreatePermissionController createPermissionController) {
        this.viewManagerModel = viewManagerModel;
        this.getPermissionViewModel = getPermissionViewModel;
        this.getPermissionController = getPermissionController;
        this.createPermissionViewModel = createPermissionViewModel;
        this.createPermissionController = createPermissionController;
        this.getPermissionViewModel.addPropertyChangeListener(this);

        title = new JLabel(getPermissionViewModel.TITLE_LABEL);

        // On initialization, get the permissions.
        this.getPermissionController.execute(null);

        JPanel buttons = new JPanel();

        createPermission = new JButton(GetPermissionViewModel.CREATE_PERMISSION_BUTTON_LABEL);
        buttons.add(createPermission);

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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(new JScrollPane(new JList<>(permissionListModel)));
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
    }
}
