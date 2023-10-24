package data_access;

import entity.project.Project;

public interface ApiDataAccessInterface {
    public Project getTodoistProject(String todoistProjectId);
    public Project createProject(Project project);
}
