package domains.project.use_case.get_project;

import domains.project.entity.Project;
import java.util.List;
public class GetProjectInteractor implements GetProjectInputBoundary {
    private final GetProjectApiDataAccessInterface getProjectApiDataAccessInterface;
    private final GetProjectSqlDataAccessInterface getProjectSqlDataAccessInterface;
    private final GetProjectOutputBoundary presenter;

    public GetProjectInteractor(
            GetProjectOutputBoundary presenter,
            GetProjectApiDataAccessInterface getProjectApiDataAccessInterface,
            GetProjectSqlDataAccessInterface getProjectSqlDataAccessInterface) {
        this.presenter = presenter;
        this.getProjectApiDataAccessInterface = getProjectApiDataAccessInterface;
        this.getProjectSqlDataAccessInterface = getProjectSqlDataAccessInterface;
    }
    @Override
    public void execute(GetProjectInputData input) {
        String userId = input.getUserId();
        List<Project> projects;
        try {
            projects = getProjectSqlDataAccessInterface.getProjects(userId);
        } catch (Exception e) {
            presenter.prepareFailView(e.getMessage());
            return;
        }

        GetProjectOutputData output = new GetProjectOutputData(projects);
        presenter.prepareSuccessView(output);
    }
}

