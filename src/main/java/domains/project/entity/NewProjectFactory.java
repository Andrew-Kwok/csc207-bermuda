package domains.project.entity;

public class NewProjectFactory {

    public static Project create(String projectID, String projectName) {
        return new Project(projectID, projectName);
    }
}
