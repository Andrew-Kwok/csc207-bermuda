package interface_adapter.permission.get_permission;

import domains.permission.entity.Permission;

import java.util.ArrayList;
import java.util.List;

public class GetPermissionState {
    private List<Permission> permissions = new ArrayList<>();
    private String getPermissionError = null;

    public GetPermissionState(GetPermissionState copy) {
        this.permissions = copy.permissions;
        this.getPermissionError = copy.getPermissionError;
    }

    public GetPermissionState() {
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public String getGetPermissionError() {
        return getPermissionError;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void setGetPermissionError(String getPermissionError) {
        this.getPermissionError = getPermissionError;
    }
}
