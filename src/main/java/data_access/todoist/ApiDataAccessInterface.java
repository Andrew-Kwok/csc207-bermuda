package data_access.todoist;

import domains.project.entity.Project;
import domains.task.entity.Task;

import java.time.ZonedDateTime;

public interface ApiDataAccessInterface {
    public Project getProject(String projectID);
    public Project createProject(String projectName);
    public Task addTask(String taskID, String projectID, String taskName,
                        String taskStatus,String taskDescription, ZonedDateTime taskDeadline);
}
