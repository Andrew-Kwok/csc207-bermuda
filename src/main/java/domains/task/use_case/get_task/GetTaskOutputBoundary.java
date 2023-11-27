package domains.task.use_case.get_task;

public interface GetTaskOutputBoundary {
    void prepareSuccessView(GetTaskOutputData getTaskOutputData);

    void prepareFailView(String error);
}
