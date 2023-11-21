package domains.task.use_case.get_task;

import domains.task.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class GetTaskInteractor implements GetTaskInputBoundary{
    private final GetTaskDataAccessInterface getTaskDataAccess;
    private final GetTaskOutputBoundary presenter;

    public GetTaskInteractor(GetTaskOutputBoundary presenter, GetTaskDataAccessInterface getTaskDataAccess){
        this.getTaskDataAccess = getTaskDataAccess;
        this.presenter = presenter;
    }

    @Override
    public ArrayList<Task> execute(GetTaskInputData getTaskInputData) {
        String projectID = getTaskInputData.getProjectID();
        ArrayList<Task> tasks;
        try {
            tasks = getTaskDataAccess.getTasks(projectID);
        } catch (Exception e) {
            presenter.prepareFailView(e.getMessage());
            return null;
        }

        GetTaskOutputData output = new GetTaskOutputData(tasks);
        presenter.prepareSuccessView(output);
        return tasks;
    }
}
