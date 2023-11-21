package interface_adapter.project.get_project;


import domains.project.use_case.get_project.GetProjectInputBoundary;
import domains.project.use_case.get_project.GetProjectInputData;
public class GetProjectController {
    final GetProjectInputBoundary getProjectInteractor;

    public GetProjectController(GetProjectInputBoundary getProjectInteractor) {
        this.getProjectInteractor = getProjectInteractor;
    }

    public void execute(String userId) {
        GetProjectInputData getProjectInputData = new GetProjectInputData(userId);
        getProjectInteractor.execute(getProjectInputData);
    }
}
