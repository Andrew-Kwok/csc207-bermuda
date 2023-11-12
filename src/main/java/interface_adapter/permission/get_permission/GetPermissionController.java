package interface_adapter.permission.get_permission;

import domains.permission.use_case.get_permission.GetPermissionInputBoundary;
import domains.permission.use_case.get_permission.GetPermissionInputData;

public class GetPermissionController {
    final GetPermissionInputBoundary getPermissionInteractor;

    public GetPermissionController(GetPermissionInputBoundary getPermissionInteractor) {
        this.getPermissionInteractor = getPermissionInteractor;
    }

    public void execute(String userId) {
        GetPermissionInputData getPermissionInputData = new GetPermissionInputData(userId);
        getPermissionInteractor.execute(getPermissionInputData);
    }
}
