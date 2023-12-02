package domains.task.use_case.close_task;


public interface CloseTaskOutputBoundary {
    void prepareSuccessView(CloseTaskOutputData taskID);

    void prepareFailView(String error);
}
