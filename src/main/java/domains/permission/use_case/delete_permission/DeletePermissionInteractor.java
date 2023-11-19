package domains.permission.use_case.delete_permission;

public class DeletePermissionInteractor implements DeletePermissionInputBoundary {
    private final DeletePermissionDataAccessInterface dataAccess;
    private final DeletePermissionOutputBoundary presenter;

    public DeletePermissionInteractor(DeletePermissionOutputBoundary presenter, DeletePermissionDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    public void execute(DeletePermissionInputData input) {
        if (input == null) {
            presenter.prepareFailView("Permission id is required");
        } else {
            try {
                dataAccess.deletePermission(input.getPermissionId());
            } catch (Exception e) {
                presenter.prepareFailView(e.getMessage());
            }
        }
    }
}
