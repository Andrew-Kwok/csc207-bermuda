package domains.task.entity;

public class NewTaskFactory {
    static public Task create(String taskID, String projectID, String taskName) {
        return new Task(taskID, projectID, taskName);
    }


    static public Task create(String taskID, String projectID, String taskName, String taskDescription) {
        return new Task(taskID, projectID, taskName, taskDescription);
    }
}
