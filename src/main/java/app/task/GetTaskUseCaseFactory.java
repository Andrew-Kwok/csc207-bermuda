package app.task;

import domains.task.use_case.get_task.GetTaskDataAccessInterface;
import domains.task.use_case.get_task.GetTaskInputBoundary;
import domains.task.use_case.get_task.GetTaskInteractor;
import domains.task.use_case.get_task.GetTaskOutputBoundary;
import interface_adapter.task.get_task.GetTaskController;
import interface_adapter.task.get_task.GetTaskPresenter;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.task.GetTaskView;

public class GetTaskUseCaseFactory {
    private GetTaskUseCaseFactory(){}

    public static GetTaskView create(ViewManagerModel viewManagerModel, GetTaskViewModel getTaskViewModel,
                                     GetTaskDataAccessInterface getTaskDAO) {

        GetTaskController getTaskController = createGetTaskUseCase(viewManagerModel, getTaskViewModel, getTaskDAO);
        return new GetTaskView(getTaskViewModel, getTaskController, viewManagerModel);
    }

    private static GetTaskController createGetTaskUseCase(ViewManagerModel viewManagerModel,
                                                          GetTaskViewModel getTaskViewModel,
                                                          GetTaskDataAccessInterface getTaskDAO) {

        GetTaskOutputBoundary getTaskOutputBoundary = new GetTaskPresenter(viewManagerModel, getTaskViewModel, getTaskDAO);

        GetTaskInputBoundary getTaskInteractor = new GetTaskInteractor(getTaskDAO, getTaskOutputBoundary);

        return new GetTaskController(getTaskInteractor);
    }
}
