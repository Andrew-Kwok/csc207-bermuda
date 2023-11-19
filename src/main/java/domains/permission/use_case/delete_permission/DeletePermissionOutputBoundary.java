package domains.permission.use_case.delete_permission;

public interface DeletePermissionOutputBoundary {
    void prepareSuccessView(DeletePermissionOutputData permission);
    void prepareFailView(String error);

}
