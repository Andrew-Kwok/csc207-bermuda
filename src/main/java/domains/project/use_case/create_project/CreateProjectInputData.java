package domains.project.use_case.create_project;

public class CreateProjectInputData {
    private final String projectName;

    public CreateProjectInputData(String projectName) {
        this.projectName = projectName;
    }

    public String getName() {
        return projectName;
    }
}
