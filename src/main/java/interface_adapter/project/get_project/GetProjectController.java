package interface_adapter.project.get_project;

import domains.project.use_case.get_project.GetProjectInputBoundary;
import domains.project.use_case.get_project.GetProjectInputData;
public class GetProjectController {
    private final String userId;
    private final GetProjectInputBoundary getProjectInteractor;
    public GetProjectController(GetProjectInputBoundary getProjectInteractor, String userId) {
        this.getProjectInteractor = getProjectInteractor;
        this.userId = userId;
    }

    public void execute() {
        getProjectInteractor.execute(
                new GetProjectInputData(userId)
        );
    }
}
