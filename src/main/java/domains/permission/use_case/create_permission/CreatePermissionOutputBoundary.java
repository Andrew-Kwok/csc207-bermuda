package domains.permission.use_case.create_permission;
public interface CreatePermissionOutputBoundary {
    void prepareSuccessView(CreatePermissionOutputData createPermissionOutputData);
    void prepareFailView(String error);
}
