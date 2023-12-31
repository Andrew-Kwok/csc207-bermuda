package interface_adapter.task.add_task;

import domains.task.use_case.add_task.AddTaskInputBoundary;
import domains.task.use_case.add_task.AddTaskInputData;

public class AddTaskController {

    final AddTaskInputBoundary addTaskUseCaseInteractor;

    public AddTaskController(AddTaskInputBoundary addTaskUseCaseInteractor) {
        this.addTaskUseCaseInteractor = addTaskUseCaseInteractor;
    }

    public void execute(String projectId, String taskName, String taskDescription) {
        projectId = projectId.trim();
        taskName = taskName.trim();
        taskDescription = taskDescription.trim();

        AddTaskInputData addTaskInputData = new AddTaskInputData(projectId, taskName, taskDescription);
        addTaskUseCaseInteractor.execute(addTaskInputData);
    }

}
