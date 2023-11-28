package interface_adapter.task.close_task;


public class CloseTaskState {
    private String taskID;
    private String closeTaskError;

    public CloseTaskState(CloseTaskState copy) {
        this.taskID = copy.taskID;
        this.closeTaskError = copy.closeTaskError;
    }

    public CloseTaskState() {

    }

    public String getTaskID() {
        return taskID;
    }

    public String getCloseTaskError() {
        return closeTaskError;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public void setCloseTaskError(String closeTaskError) {
        this.closeTaskError = closeTaskError;
    }

    @Override
    public String toString() {
        return "CloseTaskState{" +
                "taskID='" + taskID + '\'' +
                ", closeTaskError='" + closeTaskError + '\'' +
                '}';
    }
}
