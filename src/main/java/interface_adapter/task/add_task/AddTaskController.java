package interface_adapter.task.add_task;

import domains.task.use_case.add_task.AddTaskInputBoundary;
import domains.task.use_case.add_task.AddTaskInputData;

import java.time.LocalDateTime;

public class AddTaskController {

    final AddTaskInputBoundary addTaskUseCaseInteractor;
    public AddTaskController(AddTaskInputBoundary addTaskUseCaseInteractor){
        this.addTaskUseCaseInteractor = addTaskUseCaseInteractor;
    }

    public void execute(String taskName, String description, LocalDateTime deadline){
        AddTaskInputData addTaskInputData = new AddTaskInputData(taskName, description, deadline);
        addTaskUseCaseInteractor.execute(addTaskInputData);
    }

}
