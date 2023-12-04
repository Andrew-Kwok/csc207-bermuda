package domains.project.use_case.delete_project;

public class DeleteProjectInputData {
    private final String projectId;

    public DeleteProjectInputData(String projectId) {this.projectId = projectId;}

    public String getProjectId() {return projectId;}
}
