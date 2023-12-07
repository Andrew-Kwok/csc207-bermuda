package domains.task.use_case.close_task;

public class CloseTaskInteractor implements CloseTaskInputBoundary{
    private final CloseTaskDataAccessInterface dataAccess;
    private final CloseTaskOutputBoundary presenter;

    public CloseTaskInteractor(CloseTaskOutputBoundary presenter, CloseTaskDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    public void execute(CloseTaskInputData input) {
        if (input == null) {
            presenter.prepareFailView("Task id is required");
        } else {
            try {
                dataAccess.closeTask(input.getTaskID());
                presenter.prepareSuccessView(new CloseTaskOutputData(input.getTaskID()));
            } catch (Exception e) {
                presenter.prepareFailView(e.getMessage());
            }
        }
    }
}
