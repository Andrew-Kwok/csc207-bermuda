package app.project;

import domains.permission.entity.NewPermissionFactory;
import domains.user.entity.NewUserFactory;
import domains.project.use_case.create_project.*;
import domains.user.entity.User;
import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectPresenter;
import interface_adapter.project.create_project.CreateProjectViewModel;

import interface_adapter.view_model.ViewManagerModel;
import view.project.CreateProjectView;

public class CreateProjectUseCaseFactory {
    private CreateProjectUseCaseFactory(){}

    public static CreateProjectView create(
            ViewManagerModel viewManagerModel,
            CreateProjectViewModel createProjectViewModel,
            CreateProjectApiDataAccessInterface createProjectApiDAI,
            CreateProjectSqlDataAccessInterface createProjectSqlDAI) {

        CreateProjectController createProjectController = createProjectUseCase(
                viewManagerModel, createProjectViewModel, createProjectApiDAI, createProjectSqlDAI);


        return new CreateProjectView(createProjectController, createProjectViewModel, viewManagerModel);

    }

    private static CreateProjectController createProjectUseCase(ViewManagerModel viewManagerModel,
                                                          CreateProjectViewModel createProjectViewModel,
                                                          CreateProjectApiDataAccessInterface createProjectApiDAI,
                                                          CreateProjectSqlDataAccessInterface createProjectSqlDAI) {

        CreateProjectOutputBoundary createProjectOutputBoundary = new CreateProjectPresenter(viewManagerModel, createProjectViewModel);

        String projectName = null;
        String userId = null;

        CreateProjectInputData createProjectInputData = new CreateProjectInputData(projectName, userId);

        CreateProjectInputBoundary createProjectInteractor = new CreateProjectInteractor(
                createProjectInputData,
                createProjectOutputBoundary,
                createProjectApiDAI,
                createProjectSqlDAI
                );

        return new CreateProjectController(createProjectInteractor);
    }
}
