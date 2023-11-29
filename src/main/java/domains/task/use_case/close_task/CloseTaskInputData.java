package domains.task.use_case.close_task;

public class CloseTaskInputData {
    private final String taskID;

    public CloseTaskInputData(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskID() {
        return taskID;
    }
}
