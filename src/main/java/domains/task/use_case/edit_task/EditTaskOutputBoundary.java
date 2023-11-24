package domains.task.use_case.edit_task;

import domains.task.use_case.add_task.AddTaskOutputData;

public interface EditTaskOutputBoundary {
    void prepareSuccessView(EditTaskOutputData editTaskOutputData);

    void prepareFailView(String error);
}
