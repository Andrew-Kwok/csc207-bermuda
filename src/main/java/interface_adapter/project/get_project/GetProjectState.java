package interface_adapter.project.get_project;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetProjectState {

    private String userId = null;
    private List<Map<String, String>> projects = new ArrayList<>();
    private String getProjectError = null;
    private boolean initial = false;

    public GetProjectState(GetProjectState copy) {
        this.userId = copy.userId;
        this.projects = copy.projects;
        this.getProjectError = copy.getProjectError;
        this.initial = copy.initial;
    }

    public GetProjectState() {
    }

    public String getUserId() {
        return userId;
    }

    public List<Map<String, String>> getProjects() {
        return projects;
    }

    public String getGetProjectError() {
        return getProjectError;
    }

    public boolean isInitial() {
        return initial;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProjects(List<Map<String, String>> projects) {
        this.projects = projects;
    }

    public void setGetProjectError(String getProjectError) {
        this.getProjectError = getProjectError;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }
}
 