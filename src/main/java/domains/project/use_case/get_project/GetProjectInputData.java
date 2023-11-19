package domains.project.use_case.get_project;

public class GetProjectInputData {
    private final String userId;
    public GetProjectInputData(String userId) {
        this.userId = userId;
    }

    public String getUserId() { return userId; }
}
