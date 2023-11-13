package domains.permission.use_case.create_permission;
public interface CreatePermissionOutputBoundary {
    void prepareSuccessView(String permissionId);
    void prepareFailView(String error);
}
