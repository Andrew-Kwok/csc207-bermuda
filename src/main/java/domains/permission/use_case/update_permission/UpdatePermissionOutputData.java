package domains.permission.use_case.update_permission;

public class UpdatePermissionOutputData {
    private final String permissionId;

    public UpdatePermissionOutputData(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionId() {
        return permissionId;
    }
}
