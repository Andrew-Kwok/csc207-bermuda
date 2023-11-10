package domains.task.use_case.add_task;
import app.task.AddTaskUseCaseFactory;
import domains.permission.entity.Permission;
import domains.task.entity.Task;

public class AddTaskInteractor implements AddTaskInputBoundary{
    private final AddTaskDataAccessInterface addTaskDataAccessObject;
    private final AddTaskOutputBoundary addTaskPresenter;

    public AddTaskInteractor(AddTaskDataAccessInterface addTaskDataAccessObject,
                                AddTaskOutputBoundary addTaskOutputBoundary) {
        this.addTaskDataAccessObject = addTaskDataAccessObject;
        this.addTaskPresenter = addTaskOutputBoundary;
    }


    @Override
    public void execute(AddTaskInputData addTaskInputData) {
        String projectID = addTaskInputData.getProjectID();
        String content = addTaskInputData.getContent();

        if (projectID == null || content == null){
            addTaskPresenter.prepareFailView("must have projectID and content");
        }
        else{
            try{
                Task task = new Task(null, projectID, addTaskInputData.getTaskName());

                String taskID = this.addTaskDataAccessObject.addTask(projectID, task);
                AddTaskOutputData addTaskOutputData = new AddTaskOutputData(taskID);
                this.addTaskPresenter.printTaskID(addTaskOutputData);
            }
            catch (Exception e){
                addTaskPresenter.prepareFailView(e.getMessage());
            }
        }
    }
}