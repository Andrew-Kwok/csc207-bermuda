package app.project;

import domains.project.use_case.create_project.*;
import domains.project.use_case.get_project.*;
import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectPresenter;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.project.get_project.GetProjectController;
import interface_adapter.project.get_project.GetProjectPresenter;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.user.loggedin_user.LoggedInViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.project.GetProjectView;

public class GetProjectUseCaseFactory {
    private GetProjectUseCaseFactory() {}

    public static GetProjectView create(ViewManagerModel viewManagerModel,
                                        LoggedInViewModel loggedInViewModel,
                                        CreateProjectViewModel createProjectViewModel,
                                        GetProjectViewModel getProjectViewModel,
                                        CreateProjectApiDataAccessInterface createProjectApiDAI,
                                        CreateProjectSqlDataAccessInterface createProjectSqlDAI,
                                        GetProjectApiDataAccessInterface getProjectApiDAI,
                                        GetProjectSqlDataAccessInterface getProjectSqlDAI) {
        CreateProjectController createProjectController = createProjectUseCase(
                viewManagerModel,
                createProjectViewModel,
                getProjectViewModel,
                createProjectSqlDAI,
                createProjectApiDAI
        );
        GetProjectController getProjectController = getProjectUseCase(
                viewManagerModel,
                getProjectViewModel,
                getProjectSqlDAI,
                getProjectApiDAI
        );

        return new GetProjectView(
                viewManagerModel,
                loggedInViewModel,
                getProjectViewModel,
                getProjectController,
                createProjectViewModel,
                createProjectController
        );
    }

    private static CreateProjectController createProjectUseCase(ViewManagerModel viewManagerModel,
                                                                CreateProjectViewModel createProjectViewModel,
                                                                GetProjectViewModel getProjectViewModel,
                                                                CreateProjectSqlDataAccessInterface createProjectSqlDAI,
                                                                CreateProjectApiDataAccessInterface createProjectApiDAI) {
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

    private static GetProjectController getProjectUseCase(ViewManagerModel viewManagerModel,
                                                          GetProjectViewModel getProjectViewModel,
                                                          GetProjectSqlDataAccessInterface getProjectSqlDAI,
                                                          GetProjectApiDataAccessInterface getProjectApiDAI) {
        GetProjectOutputBoundary getProjectOutputBoundary = new GetProjectPresenter(
                viewManagerModel,
                getProjectViewModel
        );

        GetProjectInputBoundary getProjectInteractor = new GetProjectInteractor(
                getProjectOutputBoundary,
                getProjectSqlDAI,
                getProjectApiDAI
        );

        return new GetProjectController(getProjectInteractor);
    }

}
