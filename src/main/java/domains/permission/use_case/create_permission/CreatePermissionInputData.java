package domains.permission.use_case.create_permission;

import domains.permission.entity.Permission;

public class CreatePermissionInputData {
    private final String userId;
    private final String projectId;
    private final String permissionName;
    private final String permissionDescription;
    public CreatePermissionInputData(String userId, String projectId, String permissionName, String permissionDescription) {
        this.userId = userId;
        this.projectId = projectId;
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
    }

    public String getUserId() {
        return userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }
}
