package interface_adapter.permission.delete_permission;

import domains.permission.use_case.delete_permission.DeletePermissionInputBoundary;
import domains.permission.use_case.delete_permission.DeletePermissionInputData;

public class DeletePermissionController {
    final DeletePermissionInputBoundary deletePermissionInteractor;

    public DeletePermissionController(DeletePermissionInputBoundary deletePermissionInteractor) {
        this.deletePermissionInteractor = deletePermissionInteractor;
    }

    public void execute(String permissionId) {
        permissionId = permissionId.trim();

        DeletePermissionInputData deletePermissionInputData = new DeletePermissionInputData(permissionId);
        deletePermissionInteractor.execute(deletePermissionInputData);
    }
}
