package domains.task.use_case.edit_task;


import domains.task.entity.Task;

public class EditTaskInteractor implements EditTaskInputBoundary{
    private final EditTaskDataAccessInterface dataAccess;
    private final EditTaskOutputBoundary presenter;

    public EditTaskInteractor(EditTaskOutputBoundary presenter, EditTaskDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    public void execute(EditTaskInputData input) {
        if (input == null) {
            presenter.prepareFailView("input is required");
        } else if (input.getTaskID() == null || input.getTaskID().isEmpty()) {
            presenter.prepareFailView("task ID is null or empty");
        } else if (input.getProjectID() == null || input.getProjectID().isEmpty()) {
            presenter.prepareFailView("project ID is null or empty");
        } else if (input.getTaskName() == null || input.getTaskName().isEmpty()) {
            presenter.prepareFailView("task name is null or empty");
        } else {
            Task task = new Task(input.getTaskID(), input.getProjectID(), input.getTaskName(), input.getTaskDescription());
            try {
                dataAccess.editTask(task);
                presenter.prepareSuccessView(new EditTaskOutputData(task.getTaskID()));
            } catch (Exception e) {
                presenter.prepareFailView(e.getMessage());
            }
        }
    }
}
