package interface_adapter.project.share_project;

public class ShareProjectState {
    private String projectId;
    private String userId;
    private String otherUserName;

    public ShareProjectState(ShareProjectState copy) {
        this.projectId = copy.projectId;
        this.userId = copy.userId;
        this.otherUserName = copy.otherUserName;
    }
    public ShareProjectState() {}

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
