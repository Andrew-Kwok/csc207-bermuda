package domains.task.entity;

import java.time.ZonedDateTime;

public class NewTaskFactory {
    static public Task create(String taskID, String projectID, String taskName, String taskContent) {
        return new Task(taskID, projectID, taskName, taskContent);
    }


    static public Task create(String taskID, String projectID, String taskName, String taskContent, String taskStatus, String taskDescription, ZonedDateTime taskDeadline) {
        return new Task(taskID, projectID, taskName, taskContent, taskStatus, taskDescription, taskDeadline);
    }
}
