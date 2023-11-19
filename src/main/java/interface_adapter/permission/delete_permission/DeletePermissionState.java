package interface_adapter.permission.delete_permission;

public class DeletePermissionState {
    private String permissionId;
    private String deletePermissionError;

    public DeletePermissionState(DeletePermissionState copy) {
        this.permissionId = copy.permissionId;
        this.deletePermissionError = copy.deletePermissionError;
    }

    public DeletePermissionState() {

    }

    public String getPermissionId() {
        return permissionId;
    }

    public String getDeletePermissionError() {
        return deletePermissionError;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public void setDeletePermissionError(String deletePermissionError) {
        this.deletePermissionError = deletePermissionError;
    }

    @Override
    public String toString() {
        return "DeletePermissionState{" +
                "permissionId='" + permissionId + '\'' +
                ", deletePermissionError='" + deletePermissionError + '\'' +
                '}';
    }
}
