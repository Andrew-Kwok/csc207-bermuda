package interface_adapter.task.add_task;

import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.add_task.AddTaskOutputBoundary;
import domains.task.use_case.add_task.AddTaskOutputData;
import interface_adapter.view_model.ViewManagerModel;

public class AddTaskPresenter implements AddTaskOutputBoundary {
    public AddTaskPresenter(ViewManagerModel viewManagerModel,
                            AddTaskViewModel addTaskViewModel,
                            AddTaskDataAccessInterface addTaskDAO){

    }

    @Override
    public void printTaskID(AddTaskOutputData taskID) {

    }
}
