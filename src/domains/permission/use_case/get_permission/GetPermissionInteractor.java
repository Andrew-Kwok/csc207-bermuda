package domains.permission.use_case.get_permission;

public class GetPermissionInteractor implements GetPermissionInputBoundary {
    private final GetPermissionOutputBoundary presenter;
    private final GetPermissionInputBoundary inputBoundary;

    public GetPermissionInteractor(GetPermissionOutputBoundary presenter, GetPermissionInputBoundary inputBoundary) {
        this.presenter = presenter;
        this.inputBoundary = inputBoundary;
    }

    @Override
    public void execute(GetPermissionInputData input) {
        String userId = input.getUserId();

    }
}
