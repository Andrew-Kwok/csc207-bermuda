package domains.task.use_case.add_task;

import domains.task.entity.Task;

public class AddTaskInteractor implements AddTaskInputBoundary {
    private final AddTaskOutputBoundary addTaskPresenter;
    private final AddTaskDataAccessInterface addTaskDataAccessObject;

    public AddTaskInteractor(
            AddTaskOutputBoundary addTaskOutputBoundary,
            AddTaskDataAccessInterface addTaskDataAccessObject) {
        this.addTaskPresenter = addTaskOutputBoundary;
        this.addTaskDataAccessObject = addTaskDataAccessObject;
    }

    @Override
    public void execute(AddTaskInputData addTaskInputData) {
        if (addTaskInputData == null) {
            addTaskPresenter.prepareFailView("missing input data");
        } else if (addTaskInputData.getProjectID() == null || addTaskInputData.getProjectID().isEmpty()) {
            addTaskPresenter.prepareFailView("missing projectID");
        } else if (addTaskInputData.getTaskName() == null || addTaskInputData.getTaskName().isEmpty()) {
            addTaskPresenter.prepareFailView("missing taskName");
        } else if (addTaskInputData.getTaskContent() == null || addTaskInputData.getTaskContent().isEmpty()) {
            addTaskPresenter.prepareFailView("missing content");
        } else {
            String projectID = addTaskInputData.getProjectID();
            String taskName = addTaskInputData.getTaskName();
            String taskContent = addTaskInputData.getTaskContent();

            try {
                Task task = new Task(null, projectID, taskName, taskContent);
                addTaskDataAccessObject.addTask(task);
                AddTaskOutputData addTaskOutputData = new AddTaskOutputData(task.getTaskName());
                addTaskPresenter.prepareSuccessView(addTaskOutputData);
            } catch (Exception e) {
                addTaskPresenter.prepareFailView(e.getMessage());
            }
        }
    }
}