package domains.task.use_case.add_task;
import app.task.AddTaskUseCaseFactory;
import domains.task.entity.Task;

public class AddTaskInteractor implements AddTaskInputBoundary{
    final AddTaskDataAccessInterface addTaskDataAccessObject;
    final AddTaskOutputBoundary addTaskPresenter;
    //final AddTaskUseCaseFactory addTaskUseCaseFactory;

    public AddTaskInteractor(AddTaskDataAccessInterface addTaskDataAccessObject,
                                AddTaskOutputBoundary addTaskOutputBoundary) {
        this.addTaskDataAccessObject = addTaskDataAccessObject;
        this.addTaskPresenter = addTaskOutputBoundary;
        //this.addTaskUseCaseFactory = addTaskUseCaseFactory;
    }


    @Override
    public void execute(AddTaskInputData addTaskInputData) {
        Task task = this.addTaskDataAccessObject.addTask();
        AddTaskOutputData addTaskOutputData = new AddTaskOutputData(task);
        this.addTaskPresenter.printTaskID(addTaskOutputData);
    }
}