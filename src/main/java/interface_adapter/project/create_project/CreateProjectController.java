package interface_adapter.project.create_project;
import domains.project.use_case.create_project.CreateProjectInputBoundary;
import domains.project.use_case.create_project.CreateProjectInputData;
import domains.user.use_case.login.LoginInputBoundary;

import java.time.LocalDateTime;

public class CreateProjectController {
    final CreateProjectInputBoundary createProjectUseCaseInteractor;
    public CreateProjectController(CreateProjectInputBoundary createProjectUseCaseInteractor){
        this.createProjectUseCaseInteractor = createProjectUseCaseInteractor;
    }

    public void execute(String projectName, String description, LocalDateTime deadline){
        CreateProjectInputData createProjectInputData = new CreateProjectInputData(projectName, description, deadline);
        createProjectUseCaseInteractor.execute(createProjectInputData);
    }
}
