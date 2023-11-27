package domains.task.use_case.edit_task;

public interface EditTaskOutputBoundary {
    void prepareSuccessView(EditTaskOutputData editTaskOutputData);

    void prepareFailView(String error);
}
