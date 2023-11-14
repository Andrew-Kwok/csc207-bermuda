package domains.permission.use_case.create_permission;

import domains.permission.entity.NewPermissionFactory;
import domains.permission.entity.Permission;

public class CreatePermissionInteractor implements CreatePermissionInputBoundary {
    private final CreatePermissionOutputBoundary presenter;
    private final CreatePermissionDataAccessInterface dataAccess;

    public CreatePermissionInteractor(CreatePermissionOutputBoundary presenter, CreatePermissionDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(CreatePermissionInputData input) {
        if (input == null) {
            presenter.prepareFailView("Input is null");
        } else if (input.getUserId() == null || input.getUserId().isEmpty()) {
            presenter.prepareFailView("User ID is null or empty");
        } else if (input.getProjectId() == null || input.getProjectId().isEmpty()) {
            presenter.prepareFailView("Project ID is null or empty");
        } else if (input.getPermissionName() == null || input.getPermissionName().isEmpty()) {
            presenter.prepareFailView("Permission name is null or empty");
        } else {
            Permission permission = NewPermissionFactory.create(input.getProjectId(), input.getUserId(), input.getPermissionName(), input.getPermissionDescription());
            try {
                dataAccess.createPermission(permission);
                presenter.prepareSuccessView(permission.getPermissionID());
            } catch (Exception e) {
                presenter.prepareFailView(e.getMessage());
            }
        }
    }
}
