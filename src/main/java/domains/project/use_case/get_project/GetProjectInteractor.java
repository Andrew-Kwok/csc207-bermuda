package domains.project.use_case.get_project;

import domains.permission.entity.Permission;
import domains.project.entity.Project;

import java.util.*;

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
                    new GetProjectOutputData(getLists(result))
            );
        } catch (Exception e) {
            getProjectPresenter.prepareFailView(e.getMessage());
        }
    }

    private List<Map<String, String>> getLists(List<Project> projects) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Project project : projects) {
            result.add(project.toMap());
        }
        return result;
    }
}
