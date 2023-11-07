package domains.permission.use_case.get_permission;

public interface GetPermissionOutputBoundary {
    void prepareSuccessView(GetPermissionOutputData permission);

    void prepareFailView(String error);
}
