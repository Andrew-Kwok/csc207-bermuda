package app.permission;

import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.permission.use_case.create_permission.CreatePermissionInputBoundary;
import domains.permission.use_case.create_permission.CreatePermissionInteractor;
import domains.permission.use_case.create_permission.CreatePermissionOutputBoundary;
import domains.permission.use_case.get_permission.GetPermissionDataAccessInterface;
import domains.permission.use_case.get_permission.GetPermissionInputBoundary;
import domains.permission.use_case.get_permission.GetPermissionInteractor;
import domains.permission.use_case.get_permission.GetPermissionOutputBoundary;
import interface_adapter.permission.create_permission.CreatePermissionController;
import interface_adapter.permission.create_permission.CreatePermissionPresenter;
import interface_adapter.permission.create_permission.CreatePermissionViewModel;
import interface_adapter.permission.get_permission.GetPermissionController;
import interface_adapter.permission.get_permission.GetPermissionPresenter;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.CreatePermissionView;

import java.io.IOException;

public class CreatePermissionUseCaseFactory {
    private CreatePermissionUseCaseFactory() {}

    public static CreatePermissionView create(ViewManagerModel viewManagerModel,
                                              CreatePermissionViewModel createPermissionViewModel, GetPermissionViewModel getPermissionViewModel,
                                              CreatePermissionDataAccessInterface createPermissionDataAccessInterface, GetPermissionDataAccessInterface getPermissionDataAccessInterface) {
        CreatePermissionController createPermissionController = createPermissionUseCase(
                viewManagerModel, createPermissionViewModel, getPermissionViewModel, createPermissionDataAccessInterface
        );
        GetPermissionController getPermissionController = getPermissionUseCase(
                viewManagerModel, createPermissionViewModel, getPermissionViewModel, getPermissionDataAccessInterface
        );

        return new CreatePermissionView(
                createPermissionController, createPermissionViewModel, getPermissionController, getPermissionViewModel, viewManagerModel
        );
    }

    private static CreatePermissionController createPermissionUseCase(ViewManagerModel viewManagerModel,
                                                                      CreatePermissionViewModel createPermissionViewModel, GetPermissionViewModel getPermissionViewModel,
                                                                      CreatePermissionDataAccessInterface createPermissionDataAccessInterface) {
        CreatePermissionOutputBoundary createPermissionOutputBoundary = new CreatePermissionPresenter(viewManagerModel, createPermissionViewModel, getPermissionViewModel);

        CreatePermissionInputBoundary createPermissionInteractor = new CreatePermissionInteractor(createPermissionOutputBoundary, createPermissionDataAccessInterface);

        return new CreatePermissionController(createPermissionInteractor);
    }

    private static GetPermissionController getPermissionUseCase(ViewManagerModel viewManagerModel,
                                                                CreatePermissionViewModel createPermissionViewModel, GetPermissionViewModel getPermissionViewModel,
                                                                GetPermissionDataAccessInterface getPermissionDataAccessInterface) {
        GetPermissionOutputBoundary getPermissionOutputBoundary = new GetPermissionPresenter(viewManagerModel, createPermissionViewModel, getPermissionViewModel);

        GetPermissionInputBoundary getPermissionInteractor = new GetPermissionInteractor(getPermissionOutputBoundary, getPermissionDataAccessInterface);

        return new GetPermissionController(getPermissionInteractor);
    }
}
