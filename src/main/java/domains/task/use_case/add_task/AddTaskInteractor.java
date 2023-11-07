package domains.task.use_case.add_task;
import app.task.AddTaskUseCaseFactory;

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
        String taskID = this.addTaskDataAccessObject.addTask();
        AddTaskOutputData addTaskOutputData = new AddTaskOutputData(taskID);
        this.addTaskPresenter.printTaskID(addTaskOutputData);
    }
}