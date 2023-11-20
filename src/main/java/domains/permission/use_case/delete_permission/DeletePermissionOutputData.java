package domains.permission.use_case.delete_permission;

public class DeletePermissionOutputData {
    private final String permissionId;

    public DeletePermissionOutputData(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionId() {
        return permissionId;
    }
}
