package interface_adapter.task.add_task;

import java.time.LocalDateTime;

public class AddTaskState {

    private String projectID = "2320965999"; // TODO: Remove this default value when integrating Project & Task.
    private String taskID = "";
    private String taskName = "";
    private String taskContent = "";
    private String addTaskError = null;

    public AddTaskState() {
    }

    public String getProjectID() {
        return projectID;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public String getAddTaskError() {
        return addTaskError;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public void setAddTaskError(String addTaskError) {
        this.addTaskError = addTaskError;
    }

    @Override
    public String toString() {
        return "AddTaskState{" +
                "projectID='" + projectID + '\'' +
                ", taskID='" + taskID + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskContent='" + taskContent + '\'' +
                ", addTaskError='" + addTaskError + '\'' +
                '}';
    }
}