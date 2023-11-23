package interface_adapter.task.add_task;

import domains.task.use_case.add_task.AddTaskOutputBoundary;
import domains.task.use_case.add_task.AddTaskOutputData;
import interface_adapter.task.get_task.GetTaskState;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class AddTaskPresenter implements AddTaskOutputBoundary {

    private final AddTaskViewModel addTaskViewModel;
    private final GetTaskViewModel getTaskViewModel;
    private ViewManagerModel viewManagerModel;

    public AddTaskPresenter(
            ViewManagerModel viewManagerModel,
            AddTaskViewModel addTaskViewModel,
            GetTaskViewModel getTaskViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.addTaskViewModel = addTaskViewModel;
        this.getTaskViewModel = getTaskViewModel;
    }

    @Override
    public void prepareSuccessView(AddTaskOutputData task) {
        AddTaskState addTaskState = addTaskViewModel.getState();
        addTaskState.setTaskName(task.getTaskName());
        this.addTaskViewModel.setState(addTaskState);
        addTaskViewModel.firePropertyChanged();

        GetTaskState getTaskState = getTaskViewModel.getState();
        getTaskState.setInitial(true);
        getTaskViewModel.setState(getTaskState);
        getTaskViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(getTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        AddTaskState addTaskState = addTaskViewModel.getState();
        addTaskState.setAddTaskError(error);
        addTaskViewModel.firePropertyChanged();
    }
}