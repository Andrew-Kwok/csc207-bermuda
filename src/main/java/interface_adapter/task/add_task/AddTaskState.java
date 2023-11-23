package interface_adapter.task.add_task;

public class AddTaskState {

    private String projectID = null;
    private String taskID = "";
    private String taskName = "";
    private String taskDescription = "";
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

    public String getTaskDescription() {
        return taskDescription;
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

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
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
                ", taskDescription='" + taskDescription + '\'' +
                ", addTaskError='" + addTaskError + '\'' +
                '}';
    }
}