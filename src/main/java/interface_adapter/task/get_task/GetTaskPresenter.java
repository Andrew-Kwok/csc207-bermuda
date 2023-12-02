package interface_adapter.task.get_task;

import domains.task.use_case.get_task.GetTaskOutputBoundary;
import domains.task.use_case.get_task.GetTaskOutputData;
import interface_adapter.view_model.ViewManagerModel;

public class GetTaskPresenter implements GetTaskOutputBoundary {
    private final GetTaskViewModel getTaskViewModel;
    private ViewManagerModel viewManagerModel;

    public GetTaskPresenter(ViewManagerModel viewManagerModel, GetTaskViewModel getTaskViewModel){
        this.viewManagerModel = viewManagerModel;
        this.getTaskViewModel = getTaskViewModel;
    }
    @Override
    public void prepareSuccessView(GetTaskOutputData getTaskOutputData) {
        GetTaskState getTaskState = getTaskViewModel.getState();
        getTaskState.setTasks(getTaskOutputData.getTasks());
        getTaskViewModel.setState(getTaskState);
        getTaskViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMsg) {
        GetTaskState getTaskState = getTaskViewModel.getState();
        getTaskState.setGetTaskError(errorMsg);
        getTaskViewModel.firePropertyChanged();
    }
}
