package domains.task.use_case.get_task;

import domains.task.entity.Task;

import java.util.ArrayList;

public interface GetTaskDataAccessInterface {
    public ArrayList<Task> getTasks(String projectID) throws RuntimeException;
}
