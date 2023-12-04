package interface_adapter.project.delete_project;

import domains.project.use_case.delete_project.DeleteProjectInputBoundary;
import domains.project.use_case.delete_project.DeleteProjectInputData;

public class DeleteProjectController {
    final DeleteProjectInputBoundary deleteProjectInteractor;

    public DeleteProjectController(DeleteProjectInputBoundary deleteProjectInteractor) {
        this.deleteProjectInteractor = deleteProjectInteractor;
    }

    public void execute(String projectId) {
        projectId = projectId.trim();

        DeleteProjectInputData deleteProjectInputData = new DeleteProjectInputData(projectId);
        deleteProjectInteractor.execute(deleteProjectInputData);
    }
}
