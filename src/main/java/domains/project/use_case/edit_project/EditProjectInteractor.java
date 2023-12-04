package domains.project.use_case.edit_project;

import domains.project.entity.Project;

public class EditProjectInteractor implements EditProjectInputBoundary {

    private final EditProjectDataAccessInterface dataAccess;
    private final EditProjectOutputBoundary presenter;

    public EditProjectInteractor(EditProjectOutputBoundary presenter,
                                 EditProjectDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    public void execute(EditProjectInputData input) {
        if (input == null) {
            presenter.prepareFailView("input is required");
        } else if (input.getProjectID() == null || input.getProjectID().isEmpty()) {
            presenter.prepareFailView("project ID is null or empty");
        } else if (input.getProjectName() == null || input.getProjectName().isEmpty()) {
            presenter.prepareFailView("project name is null or empty");
        } else {
            Project project = new Project(input.getProjectID(), input.getProjectName());
            try {
                dataAccess.editProject(project);
                presenter.prepareSuccessView(new EditProjectOutputData(project.getProjectID()));
            } catch (Exception e) {
                presenter.prepareFailView(e.getMessage());
            }
        }
    }

}
