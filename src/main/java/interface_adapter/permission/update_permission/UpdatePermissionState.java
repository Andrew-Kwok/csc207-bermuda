package interface_adapter.permission.update_permission;

public class UpdatePermissionState {
    private String permissionId = "";
    private String userId = "";
    private String projectId = "";
    private String permissionName = "";
    private String permissionDescription = "";
    private String updatePermissionError = null;
    private boolean initial = false;

    public UpdatePermissionState(UpdatePermissionState copy) {
        this.permissionId = copy.permissionId;
        this.userId = copy.userId;
        this.projectId = copy.projectId;
        this.permissionName = copy.permissionName;
        this.permissionDescription = copy.permissionDescription;
        this.updatePermissionError = copy.updatePermissionError;
        this.initial = copy.initial;
    }

    public UpdatePermissionState() {
    }

    public String getPermissionId() {
        return permissionId;
    }

    public String getUserId() {
        return userId;
    }


    public String getProjectId() {
        return projectId;
    }


    public String getPermissionName() {
        return permissionName;
    }


    public String getPermissionDescription() {
        return permissionDescription;
    }

    public String getUpdatePermissionError() {
        return updatePermissionError;
    }

    public boolean isInitial() {
        return initial;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }


    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public void setUpdatePermissionError(String updatePermissionError) {
        this.updatePermissionError = updatePermissionError;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    @Override
    public String toString() {
        return "GetPermissionState{" +
                "permissionId='" + permissionId + '\'' +
                ", userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                ", updatePermissionError='" + updatePermissionError + '\'' +
                ", initial=" + initial +
                '}';
    }
}
