package domains.project.use_case.create_project;

import domains.permission.entity.PermissionFactory;
import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.user.entity.User;
public class CreateProjectInteractor {
    CreateProjectInputData createProjectInputData;
    CreateProjectDataAccessInterface createProjectDataAccessInterface;
    CreateProjectOutputBoundary createProjectPresenter;
    User user;
    CreatePermissionDataAccessInterface createPermissionDataAccessInterface;
    PermissionFactory permissionFactory;


    public CreateProjectInteractor(
            CreateProjectInputData createProjectInputData,
            CreateProjectOutputBoundary createProjectPresenter,
            CreateProjectDataAccessInterface createProjectDataAccessInterface,
            User user,
            CreatePermissionDataAccessInterface createPermissionDataAccessInterface,
            PermissionFactory permissionFactory
    ) {
        this.createProjectInputData = createProjectInputData;
        this.createProjectDataAccessInterface = createProjectDataAccessInterface;
        this.createProjectPresenter = createProjectPresenter;
        this.user = user;
        this.createPermissionDataAccessInterface = createPermissionDataAccessInterface;
        this.permissionFactory = permissionFactory;
    }

    public void execute() {
        try {
            String projectID = createProjectDataAccessInterface.createProject(
                    createProjectInputData.getName()
            );
            createPermissionDataAccessInterface.createPermission(
                    permissionFactory.create(
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
