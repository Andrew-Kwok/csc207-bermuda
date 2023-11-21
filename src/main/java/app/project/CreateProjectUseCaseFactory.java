package app.project;

import domains.project.use_case.create_project.*;
import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectPresenter;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.project.CreateProjectView;

public class CreateProjectUseCaseFactory {
    private CreateProjectUseCaseFactory() {
    }

    public static CreateProjectView create(
            ViewManagerModel viewManagerModel,
            CreateProjectViewModel createProjectViewModel,
            GetProjectViewModel getProjectViewModel,
            CreateProjectApiDataAccessInterface createProjectApiDAI,
            CreateProjectSqlDataAccessInterface createProjectSqlDAI) {

        CreateProjectController createProjectController = createProjectUseCase(
                viewManagerModel,
                createProjectViewModel,
                getProjectViewModel,
                createProjectApiDAI,
                createProjectSqlDAI
        );

        return new CreateProjectView(createProjectController, createProjectViewModel, viewManagerModel);
    }

    private static CreateProjectController createProjectUseCase(ViewManagerModel viewManagerModel,
                                                                CreateProjectViewModel createProjectViewModel,
                                                                GetProjectViewModel getProjectViewModel,
                                                                CreateProjectApiDataAccessInterface createProjectApiDAI,
                                                                CreateProjectSqlDataAccessInterface createProjectSqlDAI) {

        CreateProjectOutputBoundary createProjectOutputBoundary = new CreateProjectPresenter(
                viewManagerModel,
                createProjectViewModel,
                getProjectViewModel
        );

        CreateProjectInputBoundary createProjectInteractor = new CreateProjectInteractor(
                createProjectOutputBoundary,
                createProjectApiDAI,
                createProjectSqlDAI
        );

        return new CreateProjectController(createProjectInteractor);
    }
}
