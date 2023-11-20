package domains.task.use_case.add_task;

import domains.task.entity.Task;

public interface AddTaskDataAccessInterface {
    void addTask(String projectId, Task task) throws Exception;
}
