package use_case.add_task;

import app.CreateTaskUseCaseFactory;

public class CreateTaskInteractor implements AddTaskInputBoundary{
    final CreateTaskDataAccessInterface createTaskDataAccessObject;
    final CreateTaskOutputBoundary createTaskPresenter;
    final CreateTaskUseCaseFactory createTaskUseCaseFactory;

    public CreateTaskInteractor(CreateTaskDataAccessInterface createTaskDataAccessObject,
                             CreateTaskOutputBoundary createTaskOutputBoundary,
                             CreateTaskUseCaseFactory createTaskUseCaseFactory) {
        this.createTaskDataAccessObject = createTaskDataAccessObject;
        this.createTaskPresenter = createTaskOutputBoundary;
        this.createTaskUseCaseFactory = createTaskUseCaseFactory;
    }


    @Override
    public void execute(CreateTaskInputData createTaskInputData) {
        //name, description, deadline
        //finish
        String taskID = this.createTaskDataAccessObject.createTask();
        CreateTaskOutputData createTaskOutputData = new CreateTaskOutputData(taskID);
        this.createTaskPresenter.printTaskID(createTaskOutputData);
    }
}
