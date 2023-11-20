package domains.task.use_case.get_task;

import domains.task.entity.Task;

import java.util.ArrayList;

public interface GetTaskInputBoundary {

    ArrayList<Task> execute(GetTaskInputData getTaskInputData);
}
