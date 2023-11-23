package app.task;

import domains.project.use_case.get_project.*;
import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.add_task.AddTaskInputBoundary;
import domains.task.use_case.add_task.AddTaskInteractor;
import domains.task.use_case.add_task.AddTaskOutputBoundary;
import domains.task.use_case.get_task.GetTaskDataAccessInterface;
import domains.task.use_case.get_task.GetTaskInputBoundary;
import domains.task.use_case.get_task.GetTaskInteractor;
import domains.task.use_case.get_task.GetTaskOutputBoundary;
import interface_adapter.project.get_project.GetProjectController;
import interface_adapter.project.get_project.GetProjectPresenter;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.task.add_task.AddTaskController;
import interface_adapter.task.add_task.AddTaskPresenter;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.task.get_task.GetTaskController;
import interface_adapter.task.get_task.GetTaskPresenter;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.task.GetTaskView;

public class GetTaskUseCaseFactory {
    private GetTaskUseCaseFactory() {
    }

    public static GetTaskView create(
            ViewManagerModel viewManagerModel,
            AddTaskViewModel addTaskViewModel,
            GetTaskViewModel getTaskViewModel,
            GetProjectViewModel getProjectViewModel,
            AddTaskDataAccessInterface addTaskDataAccessInterface,
            GetTaskDataAccessInterface getTaskDataAccessInterface) {

        AddTaskController addTaskController = createAddTaskUseCase(
                viewManagerModel, addTaskViewModel, getTaskViewModel, addTaskDataAccessInterface
        );
        GetTaskController getTaskController = getTaskUseCase(
                viewManagerModel, getTaskViewModel, getTaskDataAccessInterface
        );

        return new GetTaskView(
                viewManagerModel, getProjectViewModel,
                getTaskViewModel, getTaskController,
                addTaskViewModel, addTaskController
        );
    }

    private static AddTaskController createAddTaskUseCase(ViewManagerModel viewManagerModel,
                                                          AddTaskViewModel addTaskViewModel, GetTaskViewModel getTaskViewModel,
                                                             AddTaskDataAccessInterface addTaskDataAccessInterface) {

        AddTaskOutputBoundary addTaskOutputBoundary = new AddTaskPresenter(viewManagerModel, addTaskViewModel, getTaskViewModel);

        AddTaskInputBoundary addTaskInteractor = new AddTaskInteractor(addTaskOutputBoundary, addTaskDataAccessInterface);

        return new AddTaskController(addTaskInteractor);
    }

    private static GetTaskController getTaskUseCase(ViewManagerModel viewManagerModel,
                                                    GetTaskViewModel getTaskViewModel,
                                                    GetTaskDataAccessInterface getTaskDataAccessInterface) {

        GetTaskOutputBoundary getTaskOutputBoundary = new GetTaskPresenter(viewManagerModel, getTaskViewModel);

        GetTaskInputBoundary getTaskInteractor = new GetTaskInteractor(getTaskOutputBoundary, getTaskDataAccessInterface);

        return new GetTaskController(getTaskInteractor);
    }
}
