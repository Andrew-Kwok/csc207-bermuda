package domains.permission.use_case.create_permission;

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
        Permission permission = input.getPermission();
        if (permission == null) {
            presenter.prepareFailView("Permission is null");
        } else {
            try {
                dataAccess.createPermission(permission);
            } catch (Exception e) {
                presenter.prepareFailView(e.getMessage());
            }
        }
    }
}
