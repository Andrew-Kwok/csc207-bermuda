package data_access;

import entity.project.Project;

public interface ApiDataAccessInterface {
    public Project getProject(String projectID);
    public Project createProject(String projectName);
}
