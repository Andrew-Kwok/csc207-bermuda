package domains.project.use_case.create_project;

import domains.permission.entity.NewPermissionFactory;

public class CreateProjectInteractor implements CreateProjectInputBoundary {
    CreateProjectOutputBoundary createProjectPresenter;
    CreateProjectApiDataAccessInterface createProjectApiDataAccessInterface;
    CreateProjectSqlDataAccessInterface createProjectSqlDataAccessInterface;

    public CreateProjectInteractor(
            CreateProjectOutputBoundary createProjectPresenter,
            CreateProjectApiDataAccessInterface createProjectApiDataAccessInterface,
            CreateProjectSqlDataAccessInterface createProjectSqlDataAccessInterface) {
        this.createProjectApiDataAccessInterface = createProjectApiDataAccessInterface;
        this.createProjectPresenter = createProjectPresenter;
        this.createProjectSqlDataAccessInterface = createProjectSqlDataAccessInterface;
    }

    @Override
    public void execute(CreateProjectInputData createProjectInputData) {
        if (createProjectInputData.getName().isEmpty()) {
            createProjectPresenter.prepareFailView("project name is empty");
        } else {
            try {
                String projectID = createProjectApiDataAccessInterface.createProject(
                        createProjectInputData.getName()
                );

                createProjectSqlDataAccessInterface.createPermission(
                        NewPermissionFactory.create(
                                projectID,
                                createProjectInputData.getUserId(),
                                "owner",
                                "owner of project"
                        )
                );

                createProjectPresenter.prepareSuccessView(new CreateProjectOutputData(createProjectInputData.getName()));
            } catch (Exception e) {
                createProjectPresenter.prepareFailView(e.getMessage());
            }
        }
    }
}