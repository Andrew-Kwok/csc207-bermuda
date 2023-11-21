package interface_adapter.project.create_project;

public class CreateProjectState {

    private String projectName = "";
    private String userId = "1235aefd-c2f1-45e9-ac6a-9287a7160e4b";  // TODO: remove hard-coded user id, get from logged-in user
    private String projectError = null;


    public CreateProjectState() {
    }

    public String getProjectName() {
        return projectName;
    }

    public String getUserId() {
        return userId;
    }

    public String getProjectError() {
        return projectError;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProjectError(String projectError) {
        this.projectError = projectError;
    }

    @Override
    public String toString() {
        return "CreateProjectState{" +
                "projectName='" + projectName + ", userId='" + userId + "}";
    }
}
