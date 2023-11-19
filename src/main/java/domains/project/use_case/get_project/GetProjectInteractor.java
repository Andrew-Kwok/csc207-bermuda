package domains.project.use_case.get_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import domains.permission.entity.Permission;
public class GetProjectInteractor {
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
            List<Permission> permissions =
                    sqlDataAccessInterface.getPermissions(getProjectInputData.getUserId());
            Map<String, List<String>> projectPermissions = new HashMap<>();
            for (Permission permission : permissions) {
                String projectId = permission.getProjectID();
                String projectName = apiDataAccessInterface.getProject(projectId).getProjectName();
                projectPermissions.put(
                        projectId, List.of(projectName, permission.getPermissionDescription())
                );
            }
            getProjectPresenter.prepareSuccessView(
                    new GetProjectOutputData(projectPermissions)
            );
        } catch (Exception e) {
            getProjectPresenter.prepareFailView("Internal Server Error");
        }
    }
}
