package entity.project;

import entity.utils.uuid;

public class NewProjectFactory implements ProjectFactory{

    @Override
    public Project create(String projectID, String projectName) {
        return new Project(projectID, projectName);
    }
    @Override
    public Project create(String projectID, String projectName, String projectStatus) {
        return new Project(projectID, projectName, projectStatus);
    }
}
