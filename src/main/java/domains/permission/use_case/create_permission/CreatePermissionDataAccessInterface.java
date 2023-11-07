package domains.permission.use_case.create_permission;

import domains.permission.entity.Permission;

import java.util.List;

public interface CreatePermissionDataAccessInterface {
    void createPermission(Permission permission) throws Exception;
}
