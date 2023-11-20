package interface_adapter.task.add_task;

import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.add_task.AddTaskOutputBoundary;
import domains.task.use_case.add_task.AddTaskOutputData;
import interface_adapter.permission.create_permission.CreatePermissionState;
//import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;
import interface_adapter.view_model.ViewModel;

public class AddTaskPresenter implements AddTaskOutputBoundary {

    private final AddTaskViewModel addTaskViewModel;
    //private final GetTaskViewModel getTaskViewModel;
    private ViewManagerModel viewManagerModel;

    /*public AddTaskPresenter(ViewManagerModel viewManagerModel,
                            AddTaskViewModel addTaskViewModel, GetTaskViewModel getTaskViewModel,
                            AddTaskDataAccessInterface addTaskDAO){

        this.viewManagerModel = viewManagerModel;
        //this.getTaskViewModel = getTaskViewModel;
        this.addTaskViewModel = addTaskViewModel;
    }*/

    public AddTaskPresenter (ViewManagerModel viewManagerModel,
                            AddTaskViewModel addTaskViewModel,
                            AddTaskDataAccessInterface addTaskDAO){
        this.viewManagerModel = viewManagerModel;
        this.addTaskViewModel = addTaskViewModel;
    }

    @Override
    public void prepareSuccessView(AddTaskOutputData task) {

        // On success, switch to the get task view.
        //viewManagerModel.setActiveView(getTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        AddTaskState addTaskState = addTaskViewModel.getState();
        addTaskState.setAddTaskError(error);
        addTaskViewModel.firePropertyChanged();
    }
}