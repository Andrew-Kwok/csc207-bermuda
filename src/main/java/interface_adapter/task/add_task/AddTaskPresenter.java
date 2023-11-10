package interface_adapter.task.add_task;

import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.add_task.AddTaskOutputBoundary;
import domains.task.use_case.add_task.AddTaskOutputData;
import interface_adapter.view_model.ViewManagerModel;

public class AddTaskPresenter implements AddTaskOutputBoundary {

    private final AddTaskViewModel addTaskViewModel;
    private ViewManagerModel viewManagerModel;

    public AddTaskPresenter(ViewManagerModel viewManagerModel,
                            AddTaskViewModel addTaskViewModel,
                            AddTaskDataAccessInterface addTaskDAO){

        this.viewManagerModel = viewManagerModel;
        this.addTaskViewModel = addTaskViewModel;
    }

    @Override
    public void printTaskID(AddTaskOutputData task) {
        String taskID = task.getTaskID();
        System.out.println(taskID);
    }
}