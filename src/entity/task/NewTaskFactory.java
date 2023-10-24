package entity.task;

import entity.utils.uuid;

import java.time.ZonedDateTime;

public class NewTaskFactory implements TaskFactory{
    @Override
    public Task create(String taskID, String projectID, String taskName) {
        return new Task(taskID, projectID, taskName);
    }

    @Override
    public Task create(String taskID, String projectID, String taskName, String taskStatus, String taskDescription, ZonedDateTime taskDeadline) {
        return new Task(taskID, projectID, taskName, taskStatus, taskDescription, taskDeadline);
    }
}
