package app.Project;

import domains.permission.entity.PermissionFactory;
import domains.user.entity.User;
import domains.user.entity.UserFactory;
import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.project.use_case.create_project.*;
import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectPresenter;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.project.CreateProjectView;

import javax.swing.*;
import java.io.IOException;

public class CreateProjectUseCaseFactory {
    private CreateProjectUseCaseFactory(){}

    public static CreateProjectView create(
            ViewManagerModel viewManagerModel,
            CreateProjectViewModel createProjectViewModel,
            CreateProjectDataAccessInterface createProjectDAI) {

        CreateProjectController createProjectController = createProjectUseCase(viewManagerModel, createProjectViewModel, createProjectDAI);
        return new CreateProjectView(createProjectController, createProjectViewModel, viewManagerModel);

    }

    private static CreateProjectController createProjectUseCase(ViewManagerModel viewManagerModel,
                                                          CreateProjectViewModel createProjectViewModel,
                                                          CreateProjectDataAccessInterface createProjectDAI) {

        CreateProjectOutputBoundary createProjectOutputBoundary = new CreateProjectPresenter(viewManagerModel, createProjectViewModel);

        PermissionFactory permissionFactory = new PermissionFactory();

        UserFactory userFactory = new UserFactory();

        CreateProjectInputData createProjectInputData = new CreateProjectInputData();

        CreateProjectInputBoundary createProjectInteractor = new CreateProjectInteractor(
                createProjectInputData,
                createProjectOutputBoundary,
                createProjectDAI,
                userFactory.create(),
                permissionFactory
                );

        return new CreateProjectController(createProjectInteractor);
    }
}
