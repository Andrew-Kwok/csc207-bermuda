package domains.task.use_case.get_task;

import domains.task.use_case.add_task.AddTaskOutputData;

public interface GetTaskOutputBoundary {
    void prepareSuccessView(GetTaskOutputData projectID);

    void prepareFailView(String errorMsg);
}
