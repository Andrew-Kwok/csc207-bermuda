package domains.project.use_case.create_project;

public class CreateProjectOutputData {
    private final String projectName;
    public CreateProjectOutputData(String projectName) {
        this.projectName = projectName;
    }
    public String getProjectName() {
        return projectName;
    }
}
