package domains.task.use_case.close_task;

public class CloseTaskOutputData {
    private final String taskID;

    public CloseTaskOutputData(String taskID){
        this.taskID = taskID;
    }

    public String getTaskID() {
        return taskID;
    }
}
