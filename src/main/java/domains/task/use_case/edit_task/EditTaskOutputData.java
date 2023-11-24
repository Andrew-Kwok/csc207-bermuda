package domains.task.use_case.edit_task;

public class EditTaskOutputData {
    private final String taskID;

    public EditTaskOutputData(String taskID){
        this.taskID = taskID;
    }

    public String getTaskID() {
        return taskID;
    }
}
