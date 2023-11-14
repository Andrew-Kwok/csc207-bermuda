package interface_adapter.task.add_task;

import domains.task.use_case.add_task.AddTaskInputBoundary;
import domains.task.use_case.add_task.AddTaskInputData;
import domains.user.use_case.login.LoginInputBoundary;

import java.time.LocalDateTime;

public class AddTaskController {

    final AddTaskInputBoundary addTaskUseCaseInteractor;
    public AddTaskController(AddTaskInputBoundary addTaskUseCaseInteractor){
        this.addTaskUseCaseInteractor = addTaskUseCaseInteractor;
    }

    public void execute(String taskName, String content, LocalDateTime deadline, String projectID){
        AddTaskInputData addTaskInputData = new AddTaskInputData(taskName, content, deadline, projectID);
        addTaskUseCaseInteractor.execute(addTaskInputData);
    }

}
