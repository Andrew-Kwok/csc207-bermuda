package interface_adapter.task.edit_task;


import domains.task.use_case.edit_task.EditTaskInputBoundary;
import domains.task.use_case.edit_task.EditTaskInputData;

public class EditTaskController {
    final EditTaskInputBoundary editTaskInteractor;

    public EditTaskController(EditTaskInputBoundary editTaskInteractor) {
        this.editTaskInteractor = editTaskInteractor;
    }

    public void execute(String taskID, String projectID, String taskName, String taskDescription) {
        taskID = taskID.trim();
        projectID = projectID.trim();
        taskName = taskName.trim();
        taskDescription = taskDescription.trim();

        EditTaskInputData editTaskInputData = new EditTaskInputData(
                taskID, projectID, taskName, taskDescription);

        editTaskInteractor.execute(editTaskInputData);
    }
}
