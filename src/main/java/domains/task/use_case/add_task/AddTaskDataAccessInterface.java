package domains.task.use_case.add_task;

import domains.task.entity.Task;

public interface AddTaskDataAccessInterface {
    Task addTask();  // returns ID of task
}
