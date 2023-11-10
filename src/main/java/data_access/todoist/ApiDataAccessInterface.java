package data_access.todoist;

import domains.project.entity.Project;

public interface ApiDataAccessInterface {
    public Project getProject(String projectID);
    public Project createProject(String projectName);
}
