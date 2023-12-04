package domains.project.use_case.edit_project;

public class EditProjectInputData {

    private String projectID;
    private String projectName;

    public EditProjectInputData(String projectID, String projectName) {
        this.projectID = projectID;
        this.projectName = projectName;
    }

    String getProjectID() {
        return projectID;
    }

    String getProjectName() {
        return projectName;
    }
}
