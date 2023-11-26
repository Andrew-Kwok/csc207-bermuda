package interface_adapter.share_project;

import java.util.*;

import domains.user.entity.User;
public class ShareProjectState {
    private String projectId;
    private String userId;
    private String otherUserId;
    private String otherUserName;
    private String errorMessage;

    public ShareProjectState(ShareProjectState copy) {
        this.projectId = copy.projectId;
        this.userId = copy.userId;
        this.otherUserId = copy.otherUserId;
        this.otherUserName = copy.otherUserName;
        this.errorMessage = copy.errorMessage;
    }
    public ShareProjectState() {}

    public String getProjectId() {
        return projectId;
    }

    public String getUserId() {
        return userId;
    }

    public String getOtherUserId() { return otherUserId; }

    public String getOtherUserName() { return otherUserName; }

    public String getErrorMessage() { return errorMessage; }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setOtherUserId(String otherUserId) { this.otherUserId = otherUserId; }

    public void setOtherUserName(String otherUserName) { this.otherUserName = otherUserName; }

    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}
