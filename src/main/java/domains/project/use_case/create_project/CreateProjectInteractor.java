package domains.project.use_case.create_project;

import domains.permission.entity.NewPermissionFactory;
import domains.project.entity.NewProjectFactory;
import domains.project.entity.Project;

public class CreateProjectInteractor implements CreateProjectInputBoundary {
    CreateProjectInputData createProjectInputData;
    CreateProjectApiDataAccessInterface createProjectApiDataAccessInterface;
    CreateProjectOutputBoundary createProjectPresenter;
    CreateProjectSqlDataAccessInterface createProjectSqlDataAccessInterface;


    public CreateProjectInteractor(
            CreateProjectInputData createProjectInputData,
            CreateProjectOutputBoundary createProjectPresenter,
            CreateProjectApiDataAccessInterface createProjectApiDataAccessInterface,
            CreateProjectSqlDataAccessInterface createProjectSqlDataAccessInterface) {
        this.createProjectInputData = createProjectInputData;
        this.createProjectApiDataAccessInterface = createProjectApiDataAccessInterface;
        this.createProjectPresenter = createProjectPresenter;
        this.createProjectSqlDataAccessInterface = createProjectSqlDataAccessInterface;
    }

    @Override
    public void execute(CreateProjectInputData createProjectInputData) {
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
            Project project = NewProjectFactory.create(createProjectInputData.getName(), createProjectInputData.getUserId());

            createProjectPresenter.prepareSuccessView(new CreateProjectOutputData(project.getProjectName()));
            return;
        } catch (Exception e) {
            createProjectPresenter.prepareFailView();
            return;
        }
    }
}