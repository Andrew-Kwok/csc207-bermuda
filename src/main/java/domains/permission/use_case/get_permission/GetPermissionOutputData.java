package domains.permission.use_case.get_permission;

import java.util.List;
import java.util.Map;

public class GetPermissionOutputData {
    private final List<Map<String, String>> permissions;

    public GetPermissionOutputData(List<Map<String, String>> permissions) {
        this.permissions = permissions;
    }

    public List<Map<String, String>> getPermissions() {
        return permissions;
    }
}
