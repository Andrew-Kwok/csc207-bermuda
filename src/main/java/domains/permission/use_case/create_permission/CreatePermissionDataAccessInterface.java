package domains.permission.use_case.create_permission;

import domains.permission.entity.Permission;

public interface CreatePermissionDataAccessInterface {
    void createPermission(Permission permission) throws Exception;
}
