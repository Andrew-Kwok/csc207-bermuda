package interface_adapter.project.share_project_page;

import java.util.List;
public class ShareProjectPageState {
    private String projectId;
    private String projectName;
    private String userId;
    private List<List<String>> usersNameAndId;
    private String errorMessage;

    public ShareProjectPageState(ShareProjectPageState copy) {
        this.projectId = copy.projectId;
        this.projectName = copy.projectName;
        this.userId = copy.userId;
        this.usersNameAndId = copy.usersNameAndId;
        this.errorMessage = copy.errorMessage;
    }

    public ShareProjectPageState() {}

    public String getProjectId() {
        return projectId;
    }
    public String getProjectName() {
        return projectName;
    }
    public String getUserId() {
        return userId;
    }
    public List<List<String>> getUsersNameAndId() {
        return usersNameAndId;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public void setUsersNameAndId(List<List<String>> usersNameAndId) {
        this.usersNameAndId = usersNameAndId;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    static class StateBuilder {
        private final ShareProjectPageState state = new ShareProjectPageState();
        public StateBuilder() {}

        public StateBuilder setProjectId(String projectId) {
            state.setProjectId(projectId);
            return this;
        }
        public StateBuilder setProjectName(String projectName) {
            state.setProjectName(projectName);
            return this;
        }
        public StateBuilder setUsersNameAndId(List<List<String>> usersNameAndId) {
            state.setUsersNameAndId(usersNameAndId);
            return this;
        }
        public StateBuilder setErrorMessage(String errorMessage) {
            state.setErrorMessage(errorMessage);
            return this;
        }
        public ShareProjectPageState build() {
            return state;
        }
    }

    public static StateBuilder newBuilder() {
        return new StateBuilder();
    }
}
