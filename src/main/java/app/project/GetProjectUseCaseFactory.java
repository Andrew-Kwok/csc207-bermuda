package app.project;

import domains.project.use_case.create_project.*;
import domains.project.use_case.get_project.*;
import interface_adapter.project.create_project.*;
import interface_adapter.project.get_project.*;
import interface_adapter.view_model.ViewManagerModel;
import view.project.CreateProjectView;
import view.project.GetProjectView;

public class GetProjectUseCaseFactory {
    private GetProjectUseCaseFactory() {}

    public static GetProjectView create(ViewManagerModel viewManagerModel,
                                        CreateProjectViewModel createProjectViewModel,
                                        GetProjectViewModel getProjectViewModel,
                                        CreateProjectApiDataAccessInterface createProjectApiDAI,
                                        CreateProjectSqlDataAccessInterface createProjectSqlDAI,
                                        GetProjectApiDataAccessInterface getProjectApiDAI,
                                        GetProjectSqlDataAccessInterface getProjectSqlDAI) {
        CreateProjectController createProjectController = createProjectUseCase(
                viewManagerModel, createProjectViewModel, getProjectViewModel, createProjectSqlDAI, createProjectApiDAI
        );
        GetProjectController getProjectController = getProjectUseCase(
                viewManagerModel, createProjectViewModel, getProjectViewModel, getProjectSqlDAI, getProjectApiDAI
        );

        return new GetProjectView(
                viewManagerModel, getProjectViewModel, getProjectController, createProjectViewModel, createProjectController
        );
    }

    private static CreateProjectController createProjectUseCase(ViewManagerModel viewManagerModel,
                                                                CreateProjectViewModel createProjectViewModel,
                                                                GetProjectViewModel getProjectViewModel,
                                                                CreateProjectSqlDataAccessInterface createProjectSqlDAI,
                                                                CreateProjectApiDataAccessInterface createProjectApiDAI) {
        CreateProjectOutputBoundary createProjectOutputBoundary = new CreateProjectPresenter(viewManagerModel, createProjectViewModel, getProjectViewModel);

        CreateProjectInputData createProjectInputData = new CreateProjectInputData(null, null);

        CreateProjectInputBoundary createProjectInteractor = new CreateProjectInteractor(createProjectInputData,
                createProjectOutputBoundary,
                createProjectApiDAI,
                createProjectSqlDAI);

        return new CreateProjectController(createProjectInteractor);

    }

    private static GetProjectController getProjectUseCase(ViewManagerModel viewManagerModel,
                                                          CreateProjectViewModel createProjectViewModel, GetProjectViewModel getProjectViewModel,
                                                          GetProjectSqlDataAccessInterface getProjectSqlDAI,
                                                          GetProjectApiDataAccessInterface getProjectApiDAI) {
        GetProjectOutputBoundary getProjectOutputBoundary = new GetProjectPresenter(viewManagerModel, createProjectViewModel, getProjectViewModel);

        GetProjectInputBoundary getProjectInteractor = new GetProjectInteractor(getProjectOutputBoundary, getProjectApiDAI, getProjectSqlDAI);

        return new GetProjectController(getProjectInteractor);
    }

}
