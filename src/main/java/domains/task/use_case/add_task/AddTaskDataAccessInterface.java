package domains.task.use_case.add_task;

import domains.task.entity.Task;

public interface AddTaskDataAccessInterface {
    //String addTask();  // returns ID of task
    public String addTask(String projectId, Task task);
}
