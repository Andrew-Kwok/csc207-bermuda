package domains.permission.use_case.get_permission;

import domains.permission.entity.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        List<Map<String, String>> permissionsList = getLists(permissions);
        GetPermissionOutputData output = new GetPermissionOutputData(permissionsList);
        presenter.prepareSuccessView(output);
    }

    @NotNull
    private static List<Map<String, String>> getLists(List<Permission> permissions) {
        List<Map<String, String>> permissionsList = new ArrayList<>();
        for (Permission permission : permissions) {
            permissionsList.add(permission.toMap());
        }

        return permissionsList;
    }
}
