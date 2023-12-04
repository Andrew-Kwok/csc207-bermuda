package domains.project.use_case.delete_project;

public class DeleteProjectOutputData {
    private final String projectId;

    public DeleteProjectOutputData(String projectId) { this.projectId = projectId; }

    public String getProjectId() { return projectId; }
}
