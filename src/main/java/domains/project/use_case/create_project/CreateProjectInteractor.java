package domains.project.use_case.create_project;

import domains.permission.entity.NewPermissionFactory;
import domains.user.entity.User;

public class CreateProjectInteractor implements CreateProjectInputBoundary {
    CreateProjectInputData createProjectInputData;
    CreateProjectApiDataAccessInterface createProjectApiDataAccessInterface;
    CreateProjectOutputBoundary createProjectPresenter;
    User user;
    CreateProjectSqlDataAccessInterface createProjectSqlDataAccessInterface;


    public CreateProjectInteractor(
            CreateProjectInputData createProjectInputData,
            CreateProjectOutputBoundary createProjectPresenter,
            CreateProjectApiDataAccessInterface createProjectApiDataAccessInterface,
            CreateProjectSqlDataAccessInterface createProjectSqlDataAccessInterface,
            User user
    ) {
        this.createProjectInputData = createProjectInputData;
        this.createProjectApiDataAccessInterface = createProjectApiDataAccessInterface;
        this.createProjectPresenter = createProjectPresenter;
        this.user = user;
        this.createProjectSqlDataAccessInterface = createProjectSqlDataAccessInterface;
    }

    @Override
    public void execute() {
        try {
            String projectID = createProjectApiDataAccessInterface.createProject(
                    createProjectInputData.getName()
            );
            createProjectSqlDataAccessInterface.createPermission(
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
