package interface_adapter.project.create_project;

public class CreateProjectState {

    private String projectName = "";

    public CreateProjectState(){}

    public String getProjectName(){
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString(){
        return "CreateProjectState{" +
                "projectName='" + projectName + "}";
    }
}
