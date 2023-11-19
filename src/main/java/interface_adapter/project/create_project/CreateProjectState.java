package interface_adapter.project.create_project;

public class CreateProjectState {

    private String projectName = "";
    private String userId = "";
    private String projectError = null;


    public CreateProjectState(){}

    public String getProjectName(){
        return projectName;
    }
    public String getUserId(){ return userId; }
    public String getProjectError(){
        return projectError;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public void setUserId(String userId) { this.userId = userId; }
    public void setProjectError(String projectError) {
        this.projectError = projectError;
    }

    @Override
    public String toString(){
        return "CreateProjectState{" +
                "projectName='" + projectName + ", userId='" + userId + "}";
    }
}
