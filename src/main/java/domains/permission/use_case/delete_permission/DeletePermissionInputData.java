package domains.permission.use_case.delete_permission;

public class DeletePermissionInputData {
    private final String permissionId;

    public DeletePermissionInputData(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionId() {
        return permissionId;
    }
}
