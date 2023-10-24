package entity.project;

public class Project {

    // Refer to the API documentation for the meaning of these fields.
    private final String projectID;
    private final String todoistProjectID;
    private final String projectName;
    private final String projectStatus;

    public Project(String projectID, String todoistProjectID, String projectName, String projectStatus) {
        this.projectID = projectID;
        this.todoistProjectID = todoistProjectID;
        this.projectName = projectName;
        this.projectStatus = projectStatus;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getTodoistProjectID() {
        return todoistProjectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    @Override
    public String toString() {
        return ("""
                [Project ID: %s]
                \t Todoist Project ID: %s
                \t Project Name: %s
                \t Project Status: %s
                """).
                formatted(projectID, todoistProjectID, projectName, projectStatus);
    }
}
