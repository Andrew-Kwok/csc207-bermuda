package domains.permission.use_case.update_permission;

import domains.permission.entity.Permission;

public class UpdatePermissionInteractor implements UpdatePermissionInputBoundary {
    private final UpdatePermissionDataAccessInterface dataAccess;
    private final UpdatePermissionOutputBoundary presenter;

    public UpdatePermissionInteractor(UpdatePermissionOutputBoundary presenter, UpdatePermissionDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    public void execute(UpdatePermissionInputData input) {
        if (input == null) {
            presenter.prepareFailView("permission is required");
        } else if (input.getPermissionId() == null) {
            presenter.prepareFailView("permission id is required");
        } else if (input.getUserId() == null || input.getUserId().isEmpty()) {
            presenter.prepareFailView("User ID is null or empty");
        } else if (input.getProjectId() == null || input.getProjectId().isEmpty()) {
            presenter.prepareFailView("Project ID is null or empty");
        } else if (input.getPermissionName() == null || input.getPermissionName().isEmpty()) {
            presenter.prepareFailView("Permission name is null or empty");
        } else {
            Permission permission = new Permission(input.getPermissionId(), input.getProjectId(), input.getUserId(), input.getPermissionName(), input.getPermissionDescription());
            try {
                dataAccess.updatePermission(permission);
                presenter.prepareSuccessView(new UpdatePermissionOutputData(permission.getPermissionID()));
            } catch (Exception e) {
                presenter.prepareFailView(e.getMessage());
            }
        }
    }
}
