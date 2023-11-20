package domains.task.use_case.add_task;

import domains.task.entity.Task;

public class AddTaskInteractor implements AddTaskInputBoundary {
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

        if (projectID == null || content == null) {
            addTaskPresenter.prepareFailView("must have projectID and content");
        } else {
            try {
                Task task = new Task(null, projectID, addTaskInputData.getTaskName(), content);

                AddTaskOutputData addTaskOutputData = new AddTaskOutputData();
                this.addTaskPresenter.prepareSuccessView(addTaskOutputData);
            } catch (Exception e) {
                addTaskPresenter.prepareFailView(e.getMessage());
            }
        }
    }
}