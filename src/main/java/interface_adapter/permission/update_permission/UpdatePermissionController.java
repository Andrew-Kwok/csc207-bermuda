package interface_adapter.permission.update_permission;

import domains.permission.entity.NewPermissionFactory;
import domains.permission.entity.Permission;
import domains.permission.use_case.create_permission.CreatePermissionInputBoundary;
import domains.permission.use_case.create_permission.CreatePermissionInputData;
import domains.permission.use_case.update_permission.UpdatePermissionInputBoundary;
import domains.permission.use_case.update_permission.UpdatePermissionInputData;
import domains.permission.use_case.update_permission.UpdatePermissionInteractor;

public class UpdatePermissionController {
    final UpdatePermissionInputBoundary updatePermissionInteractor;
    public UpdatePermissionController(UpdatePermissionInputBoundary updatePermissionInteractor) {
        this.updatePermissionInteractor = updatePermissionInteractor;
    }

    public void execute(String permissionId, String userId, String projectId, String permissionName, String permissionDescription) {
        permissionId = permissionId.trim();
        userId = userId.trim();
        projectId = projectId.trim();
        permissionName = permissionName.trim();
        permissionDescription = permissionDescription.trim();

        UpdatePermissionInputData updatePermissionInputData = new UpdatePermissionInputData(
                permissionId, userId, projectId, permissionName, permissionDescription);

        updatePermissionInteractor.execute(updatePermissionInputData);
    }
}
