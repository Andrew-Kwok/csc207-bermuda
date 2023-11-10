package domains.permission.use_case.update_permission;

import domains.permission.entity.Permission;

public class UpdatePermissionInputData {
    private final Permission permission;

    public UpdatePermissionInputData(Permission permission) {
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
    }

}
