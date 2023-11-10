package domains.permission.use_case.delete_permission;

public interface DeletePermissionDataAccessInterface {
    void deletePermission(String permissionId) throws Exception;
}
