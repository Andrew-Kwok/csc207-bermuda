package domains.project.use_case.edit_project;

public class EditProjectOutputData {
    private final String projectID;

    public EditProjectOutputData(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectID() {
        return projectID;
    }
}
