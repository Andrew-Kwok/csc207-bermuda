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

    public void execute(String taskName, String content, String deadline, String projectID){
        taskName = taskName.trim();
        content = content.trim();
        projectID = projectID.trim();

        System.out.println(taskName + "\n" + content + "\n" + deadline);

        AddTaskInputData addTaskInputData = new AddTaskInputData(taskName, content, deadline, projectID);
        addTaskUseCaseInteractor.execute(addTaskInputData);
    }
}
