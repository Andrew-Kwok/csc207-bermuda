package interface_adapter.project.create_project;
import java.time.LocalDateTime;

public class CreateProjectState {

    private String projectName = "";
    private String description = "";
    private LocalDateTime deadline = null;

    public CreateProjectState(){}

    public String getProjectName(){
        return projectName;
    }

    public String getDescription(){
        return description;
    }

    public LocalDateTime getDeadline(){
        return deadline;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setDeadline(LocalDateTime deadline){
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "CreateProjectState{" +
                "projectName='" + projectName + "\'" +
                "description='" + description + "\'" +
                "deadline='" + deadline.toString() + "\'" +
                "}";
    }
}
