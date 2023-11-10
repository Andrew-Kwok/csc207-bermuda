package domains.project.use_case.create_project;

public class CreateProjectInteractor {
    CreateProjectInputData createProjectInputData;
    CreateProjectDataAccessInterface createProjectDataAccessInterface;
    CreateProjectOutputBoundary createProjectPresenter;

    public CreateProjectInteractor(
            CreateProjectInputData createProjectInputData,
            CreateProjectDataAccessInterface createProjectDataAccessInterface,
            CreateProjectOutputBoundary createProjectPresenter
    ) {
        this.createProjectInputData = createProjectInputData;
        this.createProjectDataAccessInterface = createProjectDataAccessInterface;
        this.createProjectPresenter = createProjectPresenter;
    }

    public void execute() {
        try {
            createProjectDataAccessInterface.createProject(
                    createProjectInputData.getName()
            );
            createProjectPresenter.prepareSuccessView();
            return;
        } catch (Exception e) {
            createProjectPresenter.prepareFailView();
            return;
        }
    }
}
