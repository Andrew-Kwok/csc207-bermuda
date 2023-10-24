package entity.task;

import java.time.ZonedDateTime;

public interface TaskFactory {
    Task create(String todoistProjectID, String taskName, String taskStatus, String taskDescription, ZonedDateTime taskDeadline);
}
