package entity.task;

import java.time.ZonedDateTime;

public interface TaskFactory {
    Task create(String taskID, String projectID, String taskName);
    Task create(String taskID, String projectID, String taskName, String taskStatus, String taskDescription, ZonedDateTime taskDeadline);
}
