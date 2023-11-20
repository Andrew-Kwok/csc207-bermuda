package domains.task.use_case.add_task;

public class AddTaskOutputData {
    private final String taskName;

    public AddTaskOutputData(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }
}
