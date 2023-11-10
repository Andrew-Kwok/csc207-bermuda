package domains.permission.use_case.update_permission;

public class UpdatePermissionInteractor {
    private final UpdatePermissionDataAccessInterface dataAccess;
    private final UpdatePermissionOutputBoundary presenter;

    public UpdatePermissionInteractor(UpdatePermissionDataAccessInterface dataAccess, UpdatePermissionOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    public void execute(UpdatePermissionInputData input) {
        if (input == null) {
            presenter.prepareFailView("permission is required");
            return;
        } else {
            try {
                dataAccess.updatePermission(input.getPermission());
            } catch (Exception e) {
                presenter.prepareFailView(e.getMessage());
            }
        }
    }
}
