package domains.project.use_case.create_project;

import domains.permission.entity.NewPermissionFactory;
import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.user.entity.User;
public class CreateProjectInteractor implements CreateProjectInputBoundary {
    CreateProjectInputData createProjectInputData;
    CreateProjectApiDataAccessInterface createProjectApiDataAccessInterface;
    CreateProjectOutputBoundary createProjectPresenter;
    User user;
    CreatePermissionDataAccessInterface createPermissionDataAccessInterface;


    public CreateProjectInteractor(
            CreateProjectInputData createProjectInputData,
            CreateProjectOutputBoundary createProjectPresenter,
            CreateProjectApiDataAccessInterface createProjectApiDataAccessInterface,
            User user,
            CreatePermissionDataAccessInterface createPermissionDataAccessInterface
    ) {
        this.createProjectInputData = createProjectInputData;
        this.createProjectApiDataAccessInterface = createProjectApiDataAccessInterface;
        this.createProjectPresenter = createProjectPresenter;
        this.user = user;
        this.createPermissionDataAccessInterface = createPermissionDataAccessInterface;
    }
    @Override
    public void execute() {
        try {
            String projectID = createProjectApiDataAccessInterface.createProject(
                    createProjectInputData.getName()
            );
            createPermissionDataAccessInterface.createPermission(
                    NewPermissionFactory.create(
                            projectID,
                            user.getUserID(),
                            "owner",
                            "owner of project"
                    )
            );
            createProjectPresenter.prepareSuccessView();
            return;
        } catch (Exception e) {
            createProjectPresenter.prepareFailView();
            return;
        }
    }
}
