package domains.project.use_case.create_project;

import domains.permission.entity.NewPermissionFactory;
import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.user.entity.User;
public class CreateProjectInteractor implements CreateProjectInputBoundary {
    CreateProjectInputData createProjectInputData;
    CreateProjectDataAccessInterface createProjectDataAccessInterface;
    CreateProjectOutputBoundary createProjectPresenter;
    User user;
    CreatePermissionDataAccessInterface createPermissionDataAccessInterface;


    public CreateProjectInteractor(
            CreateProjectInputData createProjectInputData,
            CreateProjectOutputBoundary createProjectPresenter,
            CreateProjectDataAccessInterface createProjectDataAccessInterface,
            User user,
            CreatePermissionDataAccessInterface createPermissionDataAccessInterface
    ) {
        this.createProjectInputData = createProjectInputData;
        this.createProjectDataAccessInterface = createProjectDataAccessInterface;
        this.createProjectPresenter = createProjectPresenter;
        this.user = user;
        this.createPermissionDataAccessInterface = createPermissionDataAccessInterface;
    }
    @Override
    public void execute(CreateProjectInputData createProjectInputData) {
        try {
            String projectID = createProjectDataAccessInterface.createProject(
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
            createProjectPresenter.prepareSuccessView(createProjectInputData.getName());
            return;
        } catch (Exception e) {
            createProjectPresenter.prepareFailView("Could not create project");
            return;
        }
    }
}