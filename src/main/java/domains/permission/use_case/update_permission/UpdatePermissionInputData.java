package domains.permission.use_case.update_permission;

public class UpdatePermissionInputData {
    private final String permissionId;
    private final String userId;
    private final String projectId;
    private final String permissionName;
    private final String permissionDescription;

    public UpdatePermissionInputData(String permissionId, String userId, String projectId, String permissionName, String permissionDescription) {
        this.permissionId = permissionId;
        this.userId = userId;
        this.projectId = projectId;
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
    }

    public String getPermissionId() {
        return permissionId;
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
