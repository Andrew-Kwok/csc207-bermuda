package interface_adapter.task.close_task;

import domains.task.use_case.close_task.CloseTaskOutputBoundary;
import domains.task.use_case.close_task.CloseTaskOutputData;
import interface_adapter.permission.delete_permission.DeletePermissionState;
import interface_adapter.task.get_task.GetTaskState;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class CloseTaskPresenter implements CloseTaskOutputBoundary {
    private final GetTaskViewModel getTaskViewModel;
    private final CloseTaskViewModel closeTaskViewModel;
    private final ViewManagerModel viewManagerModel;

    public CloseTaskPresenter(ViewManagerModel viewManagerModel, GetTaskViewModel getTaskViewModel, CloseTaskViewModel closeTaskViewModel){
        this.getTaskViewModel = getTaskViewModel;
        this.closeTaskViewModel = closeTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CloseTaskOutputData task) {
        CloseTaskState closeTaskState = closeTaskViewModel.getState();

        GetTaskState getTaskState = getTaskViewModel.getState();
        getTaskState.setInitial(true);
        getTaskViewModel.setState(getTaskState);
        getTaskViewModel.firePropertyChanged();

        closeTaskState.setTaskID(task.getTaskID());
        closeTaskViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CloseTaskState closeTaskState = closeTaskViewModel.getState();

        closeTaskState.setCloseTaskError(error);
        closeTaskViewModel.firePropertyChanged();
    }
}
