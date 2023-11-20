package domains.project.use_case.get_project;

import domains.permission.entity.Permission;
import domains.project.entity.Project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetProjectInteractor implements GetProjectInputBoundary {
    private final GetProjectOutputBoundary getProjectPresenter;
    private final GetProjectSqlDataAccessInterface sqlDataAccessInterface;
    private final GetProjectApiDataAccessInterface apiDataAccessInterface;

    public GetProjectInteractor(
            GetProjectOutputBoundary getProjectPresenter,
            GetProjectSqlDataAccessInterface sqlDataAccessInterface,
            GetProjectApiDataAccessInterface apiDataAccessInterface
    ) {
        this.getProjectPresenter = getProjectPresenter;
        this.sqlDataAccessInterface = sqlDataAccessInterface;
        this.apiDataAccessInterface = apiDataAccessInterface;
    }

    public void execute(GetProjectInputData getProjectInputData) {
        try {
            List<Permission> permissions = sqlDataAccessInterface.getPermissions(getProjectInputData.getUserId());
            List<Project> projects = apiDataAccessInterface.getProjects();

            Set<String> projectIds = new HashSet<>();
            for (Permission permission : permissions) {
                projectIds.add(permission.getProjectID());
            }

            List<Project> result = new ArrayList<>();
            for (Project project : projects) {
                if (projectIds.contains(project.getProjectID())) {
                    result.add(project);
                }
            }

            getProjectPresenter.prepareSuccessView(
                    new GetProjectOutputData(result)
            );
        } catch (Exception e) {
            getProjectPresenter.prepareFailView(e.getMessage());
        }
    }
}
