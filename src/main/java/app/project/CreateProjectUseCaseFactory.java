package app.project;

import domains.project.use_case.create_project.*;
import domains.project.use_case.get_project.*;
import interface_adapter.project.create_project.*;
import interface_adapter.project.get_project.*;

import interface_adapter.view_model.ViewManagerModel;
import view.project.CreateProjectView;

public class CreateProjectUseCaseFactory {
    private CreateProjectUseCaseFactory(){}

    public static CreateProjectView create(
            ViewManagerModel viewManagerModel,
            CreateProjectViewModel createProjectViewModel,
            GetProjectViewModel getProjectViewModel,
            CreateProjectApiDataAccessInterface createProjectApiDAI,
            CreateProjectSqlDataAccessInterface createProjectSqlDAI,
            GetProjectApiDataAccessInterface getProjectApiDAI,
            GetProjectSqlDataAccessInterface getProjectSqlDAI) {

        CreateProjectController createProjectController = createProjectUseCase(
                viewManagerModel, createProjectViewModel, getProjectViewModel, createProjectApiDAI, createProjectSqlDAI);

        GetProjectController getProjectController = getProjectUseCase(
                viewManagerModel, createProjectViewModel, getProjectViewModel, getProjectApiDAI, getProjectSqlDAI);

        return new CreateProjectView(createProjectController, createProjectViewModel, viewManagerModel);

    }

    private static CreateProjectController createProjectUseCase(ViewManagerModel viewManagerModel,
                                                          CreateProjectViewModel createProjectViewModel,
                                                          GetProjectViewModel getProjectViewModel,
                                                          CreateProjectApiDataAccessInterface createProjectApiDAI,
                                                          CreateProjectSqlDataAccessInterface createProjectSqlDAI) {

        CreateProjectOutputBoundary createProjectOutputBoundary = new CreateProjectPresenter(viewManagerModel, createProjectViewModel, getProjectViewModel);

        CreateProjectInputData createProjectInputData = new CreateProjectInputData(null, null);

        CreateProjectInputBoundary createProjectInteractor = new CreateProjectInteractor(
                createProjectInputData,
                createProjectOutputBoundary,
                createProjectApiDAI,
                createProjectSqlDAI
                );

        return new CreateProjectController(createProjectInteractor);
    }

    private static GetProjectController getProjectUseCase(ViewManagerModel viewManagerModel,
                                                                CreateProjectViewModel createProjectViewModel,
                                                                GetProjectViewModel getProjectViewModel,
                                                                GetProjectApiDataAccessInterface getProjectApiDAI,
                                                                GetProjectSqlDataAccessInterface getProjectSqlDAI) {

        GetProjectOutputBoundary getProjectOutputBoundary = new GetProjectPresenter(viewManagerModel, createProjectViewModel, getProjectViewModel);


        GetProjectInputBoundary getProjectInteractor = new GetProjectInteractor(
                getProjectOutputBoundary,
                getProjectApiDAI,
                getProjectSqlDAI
        );

        return new GetProjectController(getProjectInteractor);
    }

}
