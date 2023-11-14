package domains.project.use_case.share_project;

public class ShareProjectOutputData {
    private final String projectId;
    private final String otherUserName;

    public ShareProjectOutputData(
            String projectId, String otherUserName
    ) {
        this.projectId = projectId;
        this.otherUserName = otherUserName;
    }

    public String getProjectId() { return projectId; }

    public String getOtherUserName() { return otherUserName; }
}
