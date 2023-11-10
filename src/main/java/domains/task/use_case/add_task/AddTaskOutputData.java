package domains.task.use_case.add_task;

public class AddTaskOutputData {
    private final String taskID;
    public AddTaskOutputData(String taskID){this.taskID = taskID;}

    public String getTaskID() { return taskID; }
}
