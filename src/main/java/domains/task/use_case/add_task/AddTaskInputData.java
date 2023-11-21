package domains.task.use_case.add_task;

public class AddTaskInputData {
    private String projectID;
    private String taskName;
    private String taskContent;

    public AddTaskInputData(String projectID, String taskName, String taskContent) {
        this.projectID = projectID;
        this.taskName = taskName;
        this.taskContent = taskContent;
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
