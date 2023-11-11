package domains.task.entity;

import java.time.ZonedDateTime;

public class NewTaskFactory implements TaskFactory{
    @Override
    public Task create(String taskID, String projectID, String taskName, String content) {
        return new Task(taskID, projectID, taskName, content);
    }

    @Override
    public Task create(String taskID, String projectID, String taskName, String taskStatus, String taskDescription, ZonedDateTime taskDeadline) {
        return new Task(taskID, projectID, taskName, taskStatus, taskDescription, taskDeadline);
    }
}
