package entity.project;

import entity.utils.uuid;

public class NewProjectFactory implements ProjectFactory{
    @Override
    public Project create(String todoistProjectID, String projectName, String projectStatus) {
        String projectID = uuid.newUUID();
        return new Project(projectID, todoistProjectID, projectName, projectStatus);
    }
}
