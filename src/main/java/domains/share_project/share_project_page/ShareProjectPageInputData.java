package domains.share_project.share_project_page;

public class ShareProjectPageInputData {
    private final String projectId;
    private final String projectName;
    private final String userId;

    public ShareProjectPageInputData(
            String userId, String projectId, String projectName
    ) {
        this.projectName = projectName;
        this.projectId = projectId;
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getUserId() {
        return userId;
    }
}


