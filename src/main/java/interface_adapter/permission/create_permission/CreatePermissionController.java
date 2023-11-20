package interface_adapter.permission.create_permission;

import domains.permission.use_case.create_permission.CreatePermissionInputBoundary;
import domains.permission.use_case.create_permission.CreatePermissionInputData;

public class CreatePermissionController {
    final CreatePermissionInputBoundary createPermissionInteractor;

    public CreatePermissionController(CreatePermissionInputBoundary createPermissionInteractor) {
        this.createPermissionInteractor = createPermissionInteractor;
    }

    public void execute(String userId, String projectId, String permissionName, String permissionDescription) {
        userId = userId.trim();
        projectId = projectId.trim();
        permissionName = permissionName.trim();
        permissionDescription = permissionDescription.trim();

        CreatePermissionInputData createPermissionInputData = new CreatePermissionInputData(
                userId, projectId, permissionName, permissionDescription);

        createPermissionInteractor.execute(createPermissionInputData);
    }
}
