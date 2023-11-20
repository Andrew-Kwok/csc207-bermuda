package domains.permission.use_case.update_permission;

public interface UpdatePermissionOutputBoundary {
    void prepareSuccessView(UpdatePermissionOutputData permission);

    void prepareFailView(String error);
}
