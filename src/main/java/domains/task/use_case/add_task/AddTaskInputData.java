package domains.task.use_case.add_task;

public class AddTaskInputData {
    private String projectID;
    private String taskName;
    private String taskDescription;

    public AddTaskInputData(String projectID, String taskName, String taskDescription) {
        this.projectID = projectID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    String getProjectID() {
        return projectID;
    }

    String getTaskName() {
        return taskName;
    }

    String getTaskDescription() {
        return taskDescription;
    }
}
