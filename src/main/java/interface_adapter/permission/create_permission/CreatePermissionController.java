package interface_adapter.permission.create_permission;

import domains.permission.entity.Permission;
import domains.permission.use_case.create_permission.CreatePermissionInputBoundary;
import domains.permission.use_case.create_permission.CreatePermissionInputData;

public class CreatePermissionController {
    final CreatePermissionInputBoundary createPermissionInteractor;
    public CreatePermissionController(CreatePermissionInputBoundary createPermissionInteractor) {
        this.createPermissionInteractor = createPermissionInteractor;
    }

    public void execute(String userId, String projectId, String permissionName, String permissionDescription) {
        CreatePermissionInputData createPermissionInputData = new CreatePermissionInputData(new Permission(
                userId, projectId, permissionName, permissionDescription
        ));

        createPermissionInteractor.execute(createPermissionInputData);
    }
}
