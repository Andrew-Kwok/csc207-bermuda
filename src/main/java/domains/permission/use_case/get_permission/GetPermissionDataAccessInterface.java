package domains.permission.use_case.get_permission;

import domains.permission.entity.Permission;

import java.util.List;

public interface GetPermissionDataAccessInterface {
    List<Permission> getPermissions(String permissionID) throws Exception;
}
