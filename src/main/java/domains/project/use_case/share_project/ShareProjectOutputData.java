package domains.project.use_case.share_project;

public class ShareProjectOutputData {
    private final String otherUserName;
    private final String otherUserId;
    private final String projectId;
    public ShareProjectOutputData(String otherUserName, String otherUserId, String projectId) {
        this.otherUserName = otherUserName;
        this.otherUserId = otherUserId;
        this.projectId = projectId;
    }

    public String getProjectId() { return projectId; }

    public String getOtherUserId() { return otherUserId; }

    public String getOtherUserName() { return otherUserName; }
}
