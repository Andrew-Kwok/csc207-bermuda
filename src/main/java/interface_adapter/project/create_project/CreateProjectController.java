package interface_adapter.project.create_project;
import domains.project.use_case.create_project.CreateProjectInputBoundary;
import domains.project.use_case.create_project.CreateProjectInputData;

public class CreateProjectController {
    final CreateProjectInputBoundary createProjectUseCaseInteractor;
    public CreateProjectController(CreateProjectInputBoundary createProjectUseCaseInteractor){
        this.createProjectUseCaseInteractor = createProjectUseCaseInteractor;
    }

    public void execute(String projectName){
        CreateProjectInputData createProjectInputData = new CreateProjectInputData(projectName);
        createProjectUseCaseInteractor.execute(createProjectInputData);
    }
}
