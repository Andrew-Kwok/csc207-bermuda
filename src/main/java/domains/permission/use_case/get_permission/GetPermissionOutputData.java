package domains.permission.use_case.get_permission;

import domains.permission.entity.Permission;

import java.util.List;

public class GetPermissionOutputData {
    private final List<Permission> permissions;

    public GetPermissionOutputData(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }
}
