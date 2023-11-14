package domains.task.entity;

import java.time.ZonedDateTime;

public class NewTaskFactory {
    static public Task create(String taskID, String projectID, String taskName) {
        return new Task(taskID, projectID, taskName);
    }

    static public Task create(String taskID, String projectID, String taskName, String taskStatus, String taskDescription, ZonedDateTime taskDeadline) {
        return new Task(taskID, projectID, taskName, taskStatus, taskDescription, taskDeadline);
    }
}
