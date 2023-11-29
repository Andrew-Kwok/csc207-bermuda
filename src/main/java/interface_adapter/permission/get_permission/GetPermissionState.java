package interface_adapter.permission.get_permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetPermissionState {
    private List<Map<String, String>> permissions = new ArrayList<>();
    private String getPermissionError = null;

    public GetPermissionState(GetPermissionState copy) {
        this.permissions = copy.permissions;
        this.getPermissionError = copy.getPermissionError;
    }

    public GetPermissionState() {
    }

    public List<Map<String, String>> getPermissions() {
        return permissions;
    }

    public String getGetPermissionError() {
        return getPermissionError;
    }

    public void setPermissions(List<Map<String, String>> permissions) {
        this.permissions = permissions;
    }

    public void setGetPermissionError(String getPermissionError) {
        this.getPermissionError = getPermissionError;
    }
}
