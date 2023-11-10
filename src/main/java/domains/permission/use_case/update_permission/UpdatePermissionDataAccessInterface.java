package domains.permission.use_case.update_permission;

import domains.permission.entity.Permission;

public interface UpdatePermissionDataAccessInterface {
    void updatePermission(Permission permission) throws Exception;
}