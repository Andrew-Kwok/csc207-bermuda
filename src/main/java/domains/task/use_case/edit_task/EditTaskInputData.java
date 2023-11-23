package domains.task.use_case.edit_task;

public class EditTaskInputData {
    private String taskID;
    private String projectID;
    private String taskName;
    private String taskContent;

    public EditTaskInputData(String taskID, String projectID, String taskName, String taskContent) {
        this.taskID = taskID;
        this.projectID = projectID;
        this.taskName = taskName;
        this.taskContent = taskContent;
    }

    String getTaskID(){
        return taskID;
    }
    String getProjectID() {
        return projectID;
    }

    String getTaskName() {
        return taskName;
    }

    String getTaskContent() {
        return taskContent;
    }
}
