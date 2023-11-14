package domains.project.entity;

public class NewProjectFactory {

    public static Project create(String projectID, String projectName) {
        return new Project(projectID, projectName);
    }

    public static Project create(String projectID, String projectName, String projectStatus) {
        return new Project(projectID, projectName, projectStatus);
    }
}
