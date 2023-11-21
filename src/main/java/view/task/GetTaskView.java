package view.task;

import domains.permission.entity.Permission;
import interface_adapter.permission.create_permission.CreatePermissionViewModel;
import interface_adapter.permission.delete_permission.DeletePermissionController;
import interface_adapter.permission.delete_permission.DeletePermissionViewModel;
import interface_adapter.permission.get_permission.GetPermissionController;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.permission.update_permission.UpdatePermissionViewModel;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.user.loggedin_user.LoggedInViewModel;
import interface_adapter.view_model.ViewManagerModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static constant.ViewConstant.GET_PERMISSION_VIEW_NAME;
import static constant.ViewConstant.GET_TASK_VIEW_NAME;

public class GetTaskView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = GET_TASK_VIEW_NAME;
    private final GetTaskViewModel getTaskViewModel;
    private final ViewManagerModel viewManagerModel;

    JLabel title;

    private final JButton createPermission;
    private final JButton updatePermission;
    private final JButton deletePermission;
    private final JButton goBack;

    DefaultListModel<Permission> permissionListModel = new DefaultListModel<>();
    JList<Permission> permissionList = new JList<>(permissionListModel);

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
