package domains.project.use_case.create_project;

public class CreateProjectInputData {
    private final String projectName;
    private final String userId;

    public CreateProjectInputData(String projectName, String userId) {
        this.projectName = projectName;
        this.userId = userId;
    }

    public String getName() {
        return projectName;
    }

    public String getUserId() {
        return userId;
    }
}
