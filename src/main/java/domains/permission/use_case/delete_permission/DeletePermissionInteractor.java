package domains.permission.use_case.delete_permission;

public class DeletePermissionInteractor {
    private final DeletePermissionDataAccessInterface dataAccess;
    private final DeletePermissionOutputBoundary presenter;

    public DeletePermissionInteractor(DeletePermissionDataAccessInterface dataAccess, DeletePermissionOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
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
