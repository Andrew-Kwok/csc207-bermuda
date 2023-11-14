package app.task;

import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.add_task.AddTaskInputBoundary;
import domains.task.use_case.add_task.AddTaskInteractor;
import domains.task.use_case.add_task.AddTaskOutputBoundary;
import domains.task.use_case.get_task.GetTaskOutputBoundary;
import interface_adapter.task.add_task.AddTaskController;
import interface_adapter.task.add_task.AddTaskPresenter;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.task.get_task.GetTaskController;
import interface_adapter.task.get_task.GetTaskPresenter;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.task.AddTaskView;

import javax.swing.*;
import java.io.IOException;

public class AddTaskUseCaseFactory {
    private AddTaskUseCaseFactory(){}

    public static AddTaskView create(
            ViewManagerModel viewManagerModel, AddTaskViewModel addTaskViewModel,
            GetTaskViewModel getTaskViewModel,

            AddTaskDataAccessInterface addTaskDAO) {

        try {
            AddTaskController addTaskController = createAddTaskUseCase(viewManagerModel, addTaskViewModel, addTaskDAO);
            GetTaskController getTaskController = createGetTaskUseCase(viewManagerModel, )
            return new AddTaskView(addTaskViewModel, addTaskController, getTaskViewModel, getTaskController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "AddTask View failed");
        }

        return null;
    }

    private static AddTaskController createAddTaskUseCase(ViewManagerModel viewManagerModel,
                                                          AddTaskViewModel addTaskViewModel, GetTaskViewModel getTaskViewModel,
                                                          AddTaskDataAccessInterface addTaskDAO) throws IOException {

        AddTaskOutputBoundary addTaskOutputBoundary = new AddTaskPresenter(viewManagerModel, addTaskViewModel, getTaskViewModel, addTaskDAO);

        AddTaskInputBoundary addTaskInteractor = new AddTaskInteractor(
                addTaskDAO, addTaskOutputBoundary);

        return new AddTaskController(addTaskInteractor);
    }
    private static GetTaskController createGetTaskUseCase(ViewManagerModel viewManagerModel,
                                                          AddTaskViewModel addTaskViewModel, GetTaskViewModel getTaskViewModel,
                                                          GetTaskDataAccessInterface getTaskDAO) throws IOException {

        GetTaskOutputBoundary getTaskOutputBoundary = new GetTaskPresenter(viewManagerModel, addTaskViewModel, getTaskViewModel, getTaskDAO);

        AddTaskInputBoundary getTaskInteractor = new AddTaskInteractor(
                addTaskDAO, addTaskOutputBoundary);

        return new GetTaskController(getTaskInteractor);
    }
}
