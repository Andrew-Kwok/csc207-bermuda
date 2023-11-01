package domains.permission.use_case.get_permission;

public class GetPermissionInputData {
    private final String userId;

    public GetPermissionInputData(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
