package domains.task.use_case.get_task;

import domains.task.entity.Task;

import java.util.ArrayList;
import java.util.List;

public interface GetTaskInputBoundary {

    void execute(GetTaskInputData getTaskInputData);
}
