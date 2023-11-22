package domains.share_project;

public class ShareProjectInputData {
    private final String projectId;
    private final String userId;
    private final String otherUserName;

    public ShareProjectInputData(String projectId, String userId, String otherUserName) {
        this.projectId = projectId;
        this.userId = userId;
        this.otherUserName = otherUserName;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getUserId() {
        return userId;
    }

    public String getOtherUserName() {
        return otherUserName;
    }
}
