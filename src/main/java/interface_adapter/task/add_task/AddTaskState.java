package interface_adapter.task.add_task;

import java.time.LocalDateTime;

public class AddTaskState {

    private String taskName = "";
    private String taskContent = "";
    private LocalDateTime deadline = null;
    private String projectID = "";
    private String taskID = "";
    private String addTaskError;

    public AddTaskState() {
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getAddTaskError() {
        return addTaskError;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskContent(String TaskContent) {
        this.taskContent = taskContent;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public void setAddTaskError(String addTaskError) {
        this.addTaskError = addTaskError;
    }

    @Override
    public String toString() {
        return "AddTaskState{" +
                "taskName='" + taskName + "\'" +
                "content='" + taskContent + "\'" +
                "deadline='" + deadline.toString() + "\'" +
                "}";
    }
}