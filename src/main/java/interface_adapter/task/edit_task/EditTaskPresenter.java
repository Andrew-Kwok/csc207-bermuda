package interface_adapter.task.edit_task;

import domains.task.use_case.edit_task.EditTaskOutputBoundary;
import domains.task.use_case.edit_task.EditTaskOutputData;
import interface_adapter.task.get_task.GetTaskState;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class EditTaskPresenter implements EditTaskOutputBoundary {
    private final EditTaskViewModel editTaskViewModel;
    private final GetTaskViewModel getTaskViewModel;

    private ViewManagerModel viewManagerModel;

    public EditTaskPresenter(ViewManagerModel viewManagerModel, EditTaskViewModel editTaskViewModel, GetTaskViewModel getTaskViewModel) {
        this.editTaskViewModel = editTaskViewModel;
        this.getTaskViewModel = getTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EditTaskOutputData editTaskOutputData) {
        EditTaskState editTaskState = editTaskViewModel.getState();
        editTaskState.setTaskID(editTaskOutputData.getTaskID());
        editTaskViewModel.firePropertyChanged();

        // update/refresh getTask view to reflect the new edits to task.
        GetTaskState getTaskState = getTaskViewModel.getState();
        getTaskState.setInitial(true);
        getTaskViewModel.firePropertyChanged();

        // On success, switch to the get task view.
        viewManagerModel.setActiveView(getTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        EditTaskState editTaskState = editTaskViewModel.getState();
        editTaskState.setEditTaskError(error);
        editTaskViewModel.firePropertyChanged();
    }
}
