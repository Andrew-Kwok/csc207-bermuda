package domains.task.use_case.get_task;

public class GetTaskInputData {
    private String projectID;

    public GetTaskInputData(String projectID){
        this.projectID = projectID;
    }

    String getProjectID(){
        return projectID;
    }
}
