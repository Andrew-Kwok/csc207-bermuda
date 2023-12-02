package domains.task.use_case.get_task;

import domains.task.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetTaskInteractor implements GetTaskInputBoundary {
    private final GetTaskDataAccessInterface getTaskDataAccess;
    private final GetTaskOutputBoundary presenter;

    public GetTaskInteractor(GetTaskOutputBoundary presenter, GetTaskDataAccessInterface getTaskDataAccess){
        this.getTaskDataAccess = getTaskDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(GetTaskInputData getTaskInputData) {
        if (getTaskInputData == null) {
            presenter.prepareFailView("Input data is null");
        } else if (getTaskInputData.getProjectID() == null || getTaskInputData.getProjectID().equals("")) {
            presenter.prepareFailView("Project ID is missing");
        } else {
            String projectID = getTaskInputData.getProjectID();
            try {
                List<Task> tasks = getTaskDataAccess.getTasks(projectID);
                presenter.prepareSuccessView(new GetTaskOutputData(getLists(tasks)));
            } catch (Exception e) {
                presenter.prepareFailView(e.getMessage());
            }
        }
    }

    private List<Map<String, String>> getLists(List<Task> tasks) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Task task : tasks) {
            result.add(task.toMap());
        }
        return result;
    }
}
