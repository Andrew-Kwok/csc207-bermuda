package app.task;

import domains.task.use_case.edit_task.EditTaskDataAccessInterface;
import domains.task.use_case.get_task.GetTaskDataAccessInterface;
import domains.task.use_case.get_task.GetTaskOutputBoundary;
import domains.task.use_case.get_task.GetTaskInputBoundary;
import domains.task.use_case.get_task.GetTaskInteractor;
import interface_adapter.task.edit_task.EditTaskController;
import interface_adapter.task.edit_task.EditTaskViewModel;
import interface_adapter.task.edit_task.EditTaskPresenter;
import domains.task.use_case.edit_task.EditTaskOutputBoundary;
import domains.task.use_case.edit_task.EditTaskInputBoundary;
import domains.task.use_case.edit_task.EditTaskInteractor;
import interface_adapter.task.get_task.GetTaskController;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.task.get_task.GetTaskPresenter;
import interface_adapter.view_model.ViewManagerModel;
import view.task.EditTaskView;

public class EditTaskUseCaseFactory {
    private EditTaskUseCaseFactory() {
    }

    public static EditTaskView create(ViewManagerModel viewManagerModel,
                                      EditTaskViewModel editTaskViewModel, GetTaskViewModel getTaskViewModel,
                                      EditTaskDataAccessInterface editTaskDataAccessInterface, GetTaskDataAccessInterface getTaskDataAccessInterface) {
        EditTaskController editTaskController = editTaskUseCase(
                viewManagerModel, editTaskViewModel, getTaskViewModel, editTaskDataAccessInterface
        );
        GetTaskController getTaskController = getTaskUseCase(
                viewManagerModel, getTaskViewModel, getTaskDataAccessInterface
        );

        return new EditTaskView(
                editTaskController, editTaskViewModel, getTaskController, getTaskViewModel, viewManagerModel
        );
    }

    private static EditTaskController editTaskUseCase(ViewManagerModel viewManagerModel,
                                                                      EditTaskViewModel editTaskViewModel, GetTaskViewModel getTaskViewModel,
                                                                      EditTaskDataAccessInterface editTaskDataAccessInterface) {
        EditTaskOutputBoundary editTaskOutputBoundary = new EditTaskPresenter(viewManagerModel, editTaskViewModel, getTaskViewModel);

        EditTaskInputBoundary editTaskInteractor = new EditTaskInteractor(editTaskOutputBoundary, editTaskDataAccessInterface);

        return new EditTaskController(editTaskInteractor);
    }

    private static GetTaskController getTaskUseCase(ViewManagerModel viewManagerModel,
                                                                GetTaskViewModel getTaskViewModel,
                                                                GetTaskDataAccessInterface getTaskDataAccessInterface) {
        GetTaskOutputBoundary getTaskOutputBoundary = new GetTaskPresenter(viewManagerModel, getTaskViewModel);

        GetTaskInputBoundary getTaskInteractor = new GetTaskInteractor(getTaskOutputBoundary, getTaskDataAccessInterface);

        return new GetTaskController(getTaskInteractor);
    }
}
