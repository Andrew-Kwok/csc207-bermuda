package data_access;

import entity.Project;

public interface ApiDataAccessInterface {
    public Project getProject(String projectID);
    public Project createProject(String projectName);
}
