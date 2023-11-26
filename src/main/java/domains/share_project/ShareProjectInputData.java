package domains.share_project;

public class ShareProjectInputData {
    private final String projectId;
    private final String otherUserId;
    private final String otherUserName;
    public ShareProjectInputData(String projectId, String otherUserId, String otherUserName) {
        this.projectId = projectId;
        this.otherUserId = otherUserId;
        this.otherUserName = otherUserName;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getOtherUserId() {
        return otherUserId;
    }

    public String getOtherUserName() {
        return otherUserName;
    }
}
