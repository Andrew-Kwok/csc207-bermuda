package interface_adapter.permission.create_permission;

public class CreatePermissionState {
    private String permissionId = "";
    private String userId = "";
    private String projectId = "";
    private String permissionName = "";
    private String permissionDescription = "";
    private String createPermissionError = null;

    public CreatePermissionState(CreatePermissionState copy) {
        this.permissionId = copy.permissionId;
        this.userId = copy.userId;
        this.projectId = copy.projectId;
        this.permissionName = copy.permissionName;
        this.permissionDescription = copy.permissionDescription;
        this.createPermissionError = copy.createPermissionError;
    }

    public CreatePermissionState() {
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

    public String getCreatePermissionError() {
        return createPermissionError;
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

    public void setCreatePermissionError(String createPermissionError) {
        this.createPermissionError = createPermissionError;
    }

    @Override
    public String toString() {
        return "GetPermissionState{" +
                "permissionId='" + permissionId + '\'' +
                ", userId='" + userId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                ", createPermissionError='" + createPermissionError + '\'' +
                '}';
    }
}
