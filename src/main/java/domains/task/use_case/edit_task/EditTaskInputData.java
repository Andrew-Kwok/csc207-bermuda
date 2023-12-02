package domains.task.use_case.edit_task;

public class EditTaskInputData {
    private String taskID;
    private String projectID;
    private String taskName;
    private String taskDescription;

    public EditTaskInputData(String taskID, String projectID, String taskName, String taskDescription) {
        this.taskID = taskID;
        this.projectID = projectID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    String getTaskID(){return taskID;}
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
