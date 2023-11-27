package domains.project.share_project_page;

import java.util.List;
public class ShareProjectPageOutputData {
    private final List<List<String>> usersNameAndId;
    private final String projectId;
    private final String projectName;
    private final Integer errorCode;
    public ShareProjectPageOutputData(
            List<List<String>> usersNameAndId, String projectId, String projectName, Integer errorCode
    ) {
        this.usersNameAndId = usersNameAndId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.errorCode = errorCode;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public List<List<String>> getUsersNameAndId() {
        return usersNameAndId;
    }
}
