package domains.task.use_case.add_task;

import domains.task.entity.Task;

public interface AddTaskDataAccessInterface {
    public String addTask(String projectId, Task task) throws RuntimeException;
}
