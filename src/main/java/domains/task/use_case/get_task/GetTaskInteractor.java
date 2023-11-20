package domains.task.use_case.get_task;

import domains.task.entity.Task;

import java.util.ArrayList;

public class GetTaskInteractor implements GetTaskInputBoundary{
    private final GetTaskDataAccessInterface getTaskDataAccessObject;
    private final GetTaskOutputBoundary getTaskPresenter;

    public GetTaskInteractor(GetTaskDataAccessInterface getTaskDataAccessObject,
                             GetTaskOutputBoundary getTaskOutputBoundary) {
        this.getTaskDataAccessObject = getTaskDataAccessObject;
        this.getTaskPresenter = getTaskOutputBoundary;
    }
    @Override
    public ArrayList<Task> execute(GetTaskInputData getTaskInputData) {
        String projectID = getTaskInputData.getProjectID();

        if (projectID == null){
            getTaskPresenter.prepareFailView("projectID is missing");
            return null;
        }
        else{
            try{
                //Task task = new Task(null, projectID, addTaskInputData.getTaskName(), content);
                ArrayList<Task> tasks = getTaskDataAccessObject.getTasks(projectID);

                GetTaskOutputData getTaskOutputData = new GetTaskOutputData(tasks);
                this.getTaskPresenter.prepareSuccessView(getTaskOutputData);
                return tasks;
            }
            catch (Exception e){
                getTaskPresenter.prepareFailView(e.getMessage());
                return null;
            }
        }
    }
}
