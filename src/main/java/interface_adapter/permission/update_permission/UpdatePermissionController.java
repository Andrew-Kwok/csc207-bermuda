package interface_adapter.permission.update_permission;

import domains.permission.use_case.update_permission.UpdatePermissionInputBoundary;
import domains.permission.use_case.update_permission.UpdatePermissionInputData;

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
