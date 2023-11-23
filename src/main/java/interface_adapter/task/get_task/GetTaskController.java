package interface_adapter.task.get_task;

import domains.task.use_case.get_task.GetTaskInputBoundary;
import domains.task.use_case.get_task.GetTaskInputData;

public class GetTaskController {
    final GetTaskInputBoundary getTaskUseCaseInteractor;
    public GetTaskController(GetTaskInputBoundary getTaskUseCaseInteractor){
        this.getTaskUseCaseInteractor = getTaskUseCaseInteractor;
    }

    public void execute(String projectID){
        projectID = projectID.trim();

        GetTaskInputData getTaskInputData = new GetTaskInputData(projectID);
        getTaskUseCaseInteractor.execute(getTaskInputData);
    }
}
