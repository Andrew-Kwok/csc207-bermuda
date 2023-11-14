package domains.permission.use_case.create_permission;

public class CreatePermissionOutputData {
    private final String permissionId;

    public CreatePermissionOutputData(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionId() {
        return permissionId;
    }
}
