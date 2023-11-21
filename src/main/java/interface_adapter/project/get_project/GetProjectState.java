package interface_adapter.project.get_project;


import domains.project.entity.Project;

import java.util.ArrayList;
import java.util.List;
public class GetProjectState {

    private String userId = null;
    private List<Project> projects = new ArrayList<>();
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

    public List<Project> getProjects() {
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

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void setGetProjectError(String getProjectError) {
        this.getProjectError = getProjectError;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }
}
 