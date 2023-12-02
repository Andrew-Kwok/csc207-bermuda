package interface_adapter.task.close_task;

import domains.task.use_case.close_task.CloseTaskInputBoundary;
import domains.task.use_case.close_task.CloseTaskInputData;

public class CloseTaskController {
    final CloseTaskInputBoundary closeTaskInteractor;

    public CloseTaskController(CloseTaskInputBoundary closeTaskInteractor) {
        this.closeTaskInteractor = closeTaskInteractor;
    }

    public void execute(String taskID) {
        taskID = taskID.trim();

        CloseTaskInputData closeTaskInputData = new CloseTaskInputData(taskID);
        closeTaskInteractor.execute(closeTaskInputData);
    }
}
