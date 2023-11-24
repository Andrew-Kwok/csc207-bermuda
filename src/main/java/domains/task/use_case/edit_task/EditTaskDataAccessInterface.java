package domains.task.use_case.edit_task;

import domains.task.entity.Task;

public interface EditTaskDataAccessInterface {
    void editTask(Task task) throws Exception;
}
