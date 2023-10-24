package entity.task;

import entity.utils.uuid;

import java.time.ZonedDateTime;

public class NewTaskFactory implements TaskFactory{
    @Override
    public Task create(String todoistTaskID, String taskName, String taskStatus, String taskDescription, ZonedDateTime taskDeadline) {
        String taskID = uuid.newUUID();
        return new Task(taskID, todoistTaskID, taskName, taskStatus, taskDescription, taskDeadline);
    }
}
