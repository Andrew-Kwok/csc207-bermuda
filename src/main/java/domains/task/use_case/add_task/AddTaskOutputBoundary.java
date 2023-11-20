package domains.task.use_case.add_task;

public interface AddTaskOutputBoundary {
    void prepareSuccessView(AddTaskOutputData taskID);

    void prepareFailView(String error);
}
