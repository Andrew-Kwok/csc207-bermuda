package interface_adapter.project.get_project;

import domains.permission.entity.Permission;
import domains.project.entity.Project;

import java.util.ArrayList;
import java.util.List;
public class GetProjectState {

    private List<Project> projects = new ArrayList<>();
    private String getProjectError = null;

    public GetProjectState(GetProjectState copy) {
        this.projects = copy.projects;
        this.getProjectError = copy.getProjectError;
    }

    public GetProjectState() {
    }

    public List<Project> getProjects() {
        return projects;
    }

    public String getGetProjectError() {
        return getProjectError;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    public void setGetProjectError(String getProjectError) {
        this.getProjectError = getProjectError;
    }
}

