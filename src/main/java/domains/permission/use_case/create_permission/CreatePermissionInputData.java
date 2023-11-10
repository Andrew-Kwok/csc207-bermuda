package domains.permission.use_case.create_permission;

import domains.permission.entity.Permission;

public class CreatePermissionInputData {
    private final Permission permission;

    public CreatePermissionInputData(Permission permission) {
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
    }
}
