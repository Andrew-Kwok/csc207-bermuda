package app.project;


import domains.project.use_case.edit_project.EditProjectDataAccessInterface;
import domains.project.use_case.edit_project.EditProjectInputBoundary;
import domains.project.use_case.edit_project.EditProjectInteractor;
import domains.project.use_case.edit_project.EditProjectOutputBoundary;
import domains.project.use_case.get_project.*;
import interface_adapter.project.edit_project.EditProjectController;
import interface_adapter.project.edit_project.EditProjectPresenter;
import interface_adapter.project.edit_project.EditProjectViewModel;
import interface_adapter.project.get_project.GetProjectController;
import interface_adapter.project.get_project.GetProjectPresenter;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.project.EditProjectView;

public class EditProjectUseCaseFactory {
    private EditProjectUseCaseFactory() {
    }

    public static EditProjectView create(ViewManagerModel viewManagerModel,
                                         EditProjectViewModel editProjectViewModel,
                                         GetProjectViewModel getProjectViewModel,
                                         EditProjectDataAccessInterface editProjectDataAccessInterface,
                                         GetProjectApiDataAccessInterface getProjectApiDataAccessInterface,
                                         GetProjectSqlDataAccessInterface getProjectSqlDataAccessInterface) {
        EditProjectController editProjectController = editProjectUseCase(
                viewManagerModel, editProjectViewModel, getProjectViewModel, editProjectDataAccessInterface
        );
        GetProjectController getProjectController = getProjectUseCase(
                viewManagerModel, getProjectViewModel, getProjectApiDataAccessInterface, getProjectSqlDataAccessInterface
        );

        return new EditProjectView(
                editProjectController, editProjectViewModel, getProjectController, getProjectViewModel, viewManagerModel
        );
    }

    private static EditProjectController editProjectUseCase(ViewManagerModel viewManagerModel,
                                                      EditProjectViewModel editProjectViewModel, GetProjectViewModel getProjectViewModel,
                                                      EditProjectDataAccessInterface editProjectDataAccessInterface) {
        EditProjectOutputBoundary editProjectOutputBoundary = new EditProjectPresenter(viewManagerModel, editProjectViewModel, getProjectViewModel);

        EditProjectInputBoundary editProjectInteractor = new EditProjectInteractor(editProjectOutputBoundary, editProjectDataAccessInterface);

        return new EditProjectController(editProjectInteractor);
    }

    private static GetProjectController getProjectUseCase(ViewManagerModel viewManagerModel,
                                                    GetProjectViewModel getProjectViewModel,
                                                    GetProjectApiDataAccessInterface getProjectApiDataAccessInterface,
                                                    GetProjectSqlDataAccessInterface getProjectSqlDataAccessInterface) {
        GetProjectOutputBoundary getProjectOutputBoundary = new GetProjectPresenter(viewManagerModel, getProjectViewModel);

        GetProjectInputBoundary getProjectInteractor = new GetProjectInteractor(
                getProjectOutputBoundary,
                getProjectSqlDataAccessInterface,
                getProjectApiDataAccessInterface
                );

        return new GetProjectController(getProjectInteractor);
    }
}
