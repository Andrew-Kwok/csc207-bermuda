package interface_adapter.project.delete_project;

public class DeleteProjectState {
    private String projectId;
    private String deleteProjectError;

    public DeleteProjectState(DeleteProjectState copy) {
        this.projectId = copy.projectId;
        this.deleteProjectError = copy.deleteProjectError;
    }

    public DeleteProjectState() {}

    public String getProjectId() { return projectId; }
    public String getDeleteProjectError() { return deleteProjectError; }

    public void setProjectId(String projectId) { this.projectId = projectId;}
    public void setDeleteProjectError(String deleteProjectError) {
        this.deleteProjectError = deleteProjectError;
    }

    @Override
    public String toString() {
        return "DeleteProjectState{" +
                "projectId='" + projectId + '\'' +
                ", deleteProjectError='" + deleteProjectError + '\'' +
                "}";
    }
}
