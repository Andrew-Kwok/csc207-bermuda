package domains.permission.use_case.get_permission;

import domains.permission.entity.Permission;

import java.util.List;

public class GetPermissionInteractor implements GetPermissionInputBoundary {
    private final GetPermissionDataAccessInterface dataAccess;
    private final GetPermissionOutputBoundary presenter;

    public GetPermissionInteractor(GetPermissionOutputBoundary presenter,
                                   GetPermissionDataAccessInterface dataAccessInterface) {
        this.dataAccess = dataAccessInterface;
        this.presenter = presenter;
    }

    @Override
    public void execute(GetPermissionInputData input) {
        String userId = input.getUserId();
        List<Permission> permissions;
        try {
            permissions = dataAccess.getPermissions(userId);
        } catch (Exception e) {
            presenter.prepareFailView(e.getMessage());
            return;
        }

        GetPermissionOutputData output = new GetPermissionOutputData(permissions);
        presenter.prepareSuccessView(output);
    }
}
