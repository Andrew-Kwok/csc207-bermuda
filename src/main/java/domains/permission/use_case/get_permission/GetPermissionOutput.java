package domains.permission.use_case.get_permission;

import domains.permission.entity.Permission;

import java.util.List;

public class GetPermissionOutput {
    private final List<Permission> permissions;

    public GetPermissionOutput(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
