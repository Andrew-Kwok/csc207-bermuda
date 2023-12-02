package interface_adapter.task.edit_task;


public class EditTaskState {
    private String taskID = "";
    private String projectID = "";
    private String taskName = "";
    private String taskDescription = "";
    private String editTaskError = null;
    private boolean initial = false;

    public EditTaskState(EditTaskState copy) {
        this.taskID = copy.taskID;
        this.projectID = copy.projectID;
        this.taskName = copy.taskName;
        this.taskDescription = copy.taskDescription;
        this.editTaskError = copy.editTaskError;
        this.initial = copy.initial;
    }

    public EditTaskState() {
    }

    public String getTaskID() {
        return taskID;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getEditTaskError() {
        return editTaskError;
    }

    public boolean isInitial() {
        return initial;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setEditTaskError(String editTaskError) {
        this.editTaskError = editTaskError;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    @Override
    public String toString() {
        return "GetPermissionState{" +
                "taskID='" + taskID + '\'' +
                ", projectID='" + projectID + '\'' +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", editTaskError='" + editTaskError + '\'' +
                ", initial=" + initial +
                '}';
    }
}
