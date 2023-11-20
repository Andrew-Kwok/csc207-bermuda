package app.task;

import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.add_task.AddTaskInputBoundary;
import domains.task.use_case.add_task.AddTaskInteractor;
import domains.task.use_case.add_task.AddTaskOutputBoundary;
import domains.task.use_case.get_task.GetTaskOutputBoundary;
import interface_adapter.task.add_task.AddTaskController;
import interface_adapter.task.add_task.AddTaskPresenter;
import interface_adapter.task.add_task.AddTaskState;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.task.get_task.GetTaskController;
import interface_adapter.task.get_task.GetTaskPresenter;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.task.AddTaskView;

import javax.swing.*;
import java.io.IOException;

public class AddTaskUseCaseFactory {
    private AddTaskUseCaseFactory() {
    }

    public static AddTaskView create(
            ViewManagerModel viewManagerModel, AddTaskViewModel addTaskViewModel, GetTaskViewModel getTaskViewModel,
            AddTaskDataAccessInterface addTaskDAO) {

        AddTaskController addTaskController = createAddTaskUseCase(viewManagerModel, addTaskViewModel, getTaskViewModel, addTaskDAO);
        GetTaskController getTaskController = createGetTaskUseCase();

        return new AddTaskView(addTaskViewModel, addTaskController, getTaskViewModel, getTaskController, viewManagerModel);
    }

    private static AddTaskController createAddTaskUseCase(
            ViewManagerModel viewManagerModel, AddTaskViewModel addTaskViewModel, GetTaskViewModel getTaskViewModel,
            AddTaskDataAccessInterface addTaskDAO) {

        AddTaskOutputBoundary addTaskOutputBoundary = new AddTaskPresenter(viewManagerModel, addTaskViewModel);
        AddTaskInputBoundary addTaskInteractor = new AddTaskInteractor(addTaskOutputBoundary, addTaskDAO);
        return new AddTaskController(addTaskInteractor);
    }

    private static GetTaskController createGetTaskUseCase() {
        return null;
    }
}
