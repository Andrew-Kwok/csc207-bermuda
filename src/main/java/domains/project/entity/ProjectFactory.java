package domains.project.entity;

public interface ProjectFactory {

    Project create(String projectID, String projectName);
    Project create(String projectID, String projectName, String projectStatus);

}
