package interface_adapter.project.edit_project;

import domains.project.use_case.edit_project.EditProjectInputBoundary;
import domains.project.use_case.edit_project.EditProjectInputData;

public class EditProjectController {

    final EditProjectInputBoundary editProjectInteractor;

    public EditProjectController(EditProjectInputBoundary editProjectInteractor) {
        this.editProjectInteractor = editProjectInteractor;
    }

    public void execute(String projectId, String projectName) {
        projectId = projectId.trim();
        projectName = projectName.trim();

        EditProjectInputData editProjectInputData = new EditProjectInputData(
                projectId, projectName);

        editProjectInteractor.execute(editProjectInputData);
    }
}
