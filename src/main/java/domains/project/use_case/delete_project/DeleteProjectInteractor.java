package domains.project.use_case.delete_project;

public class DeleteProjectInteractor implements DeleteProjectInputBoundary {

    private final DeleteProjectApiDataAccessInterface apiDataAccess;

    private DeleteProjectSqlDataAccessInterface sqlDataAccess;
    private final DeleteProjectOutputBoundary presenter;

    public DeleteProjectInteractor(DeleteProjectOutputBoundary presenter,
                                   DeleteProjectApiDataAccessInterface apiDataAccess,
                                   DeleteProjectSqlDataAccessInterface sqlDataAccess) {
        this.presenter = presenter;
        this.apiDataAccess = apiDataAccess;
        this.sqlDataAccess = sqlDataAccess;
    }

    public void execute(DeleteProjectInputData input) {
        if (input == null) {
            presenter.prepareFailView("Project id is required");
        } else {
            try {
                apiDataAccess.deleteProject(input.getProjectId());
                sqlDataAccess.deletePermissionByProjectId(input.getProjectId());
            } catch (Exception e) {
                presenter.prepareFailView(e.getMessage());
            }
            presenter.prepareSuccessView(new DeleteProjectOutputData(input.getProjectId()));
        }
    }
}
