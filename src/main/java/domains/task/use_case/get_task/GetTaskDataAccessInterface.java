package domains.task.use_case.get_task;

import domains.permission.entity.Permission;
import domains.task.entity.Task;

import java.util.ArrayList;
import java.util.List;

public interface GetTaskDataAccessInterface {
    public ArrayList<Task> getTasks(String projectID) throws Exception;
}
