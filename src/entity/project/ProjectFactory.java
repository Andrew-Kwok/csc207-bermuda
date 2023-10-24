package entity.project;

public interface ProjectFactory {
    Project create(String todoistProjectID, String projectName, String projectStatus);
}
