package domains.project.use_case.create_project;

public class CreateProjectInputData {
    private String name;
    public CreateProjectInputData(String name) {
        this.name = name;
    }
    public String getName() { return name; }
}
