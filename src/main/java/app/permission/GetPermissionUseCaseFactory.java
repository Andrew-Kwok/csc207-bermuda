package app.permission;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import domains.permission.entity.NewPermissionFactory;
import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.permission.use_case.create_permission.CreatePermissionInputBoundary;
import domains.permission.use_case.create_permission.CreatePermissionInteractor;
import domains.permission.use_case.create_permission.CreatePermissionOutputBoundary;
import domains.permission.use_case.delete_permission.DeletePermissionDataAccessInterface;
import domains.permission.use_case.delete_permission.DeletePermissionInputBoundary;
import domains.permission.use_case.delete_permission.DeletePermissionInteractor;
import domains.permission.use_case.delete_permission.DeletePermissionOutputBoundary;
import domains.permission.use_case.get_permission.GetPermissionDataAccessInterface;
import domains.permission.use_case.get_permission.GetPermissionInputBoundary;
import domains.permission.use_case.get_permission.GetPermissionInteractor;
import domains.permission.use_case.get_permission.GetPermissionOutputBoundary;
import domains.permission.use_case.update_permission.UpdatePermissionDataAccessInterface;
import interface_adapter.permission.create_permission.CreatePermissionController;
import interface_adapter.permission.create_permission.CreatePermissionPresenter;
import interface_adapter.permission.create_permission.CreatePermissionViewModel;
import interface_adapter.permission.delete_permission.DeletePermissionController;
import interface_adapter.permission.delete_permission.DeletePermissionPresenter;
import interface_adapter.permission.delete_permission.DeletePermissionViewModel;
import interface_adapter.permission.get_permission.GetPermissionController;
import interface_adapter.permission.get_permission.GetPermissionPresenter;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.permission.update_permission.UpdatePermissionViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.permission.GetPermissionView;

public class GetPermissionUseCaseFactory {
    private GetPermissionUseCaseFactory() {}

    public static GetPermissionView create(ViewManagerModel viewManagerModel,
                                           CreatePermissionViewModel createPermissionViewModel, CreatePermissionDataAccessInterface createPermissionDataAccessInterface,
                                           GetPermissionViewModel getPermissionViewModel, GetPermissionDataAccessInterface getPermissionDataAccessInterface,
                                           UpdatePermissionViewModel updatePermissionViewModel, UpdatePermissionDataAccessInterface updatePermissionDataAccessInterface,
                                           DeletePermissionViewModel deletePermissionViewModel, DeletePermissionDataAccessInterface deletePermissionDataAccessInterface) {
        CreatePermissionController createPermissionController = createPermissionUseCase(
                viewManagerModel, createPermissionViewModel, getPermissionViewModel, createPermissionDataAccessInterface
        );
        GetPermissionController getPermissionController = getPermissionUseCase(
                viewManagerModel, getPermissionViewModel, getPermissionDataAccessInterface
        );
        DeletePermissionController deletePermissionController = deletePermissionUseCase(
                viewManagerModel, getPermissionViewModel, deletePermissionViewModel, deletePermissionDataAccessInterface
        );

        return new GetPermissionView(
                viewManagerModel,
                getPermissionViewModel, getPermissionController,
                createPermissionViewModel, updatePermissionViewModel,
                deletePermissionViewModel, deletePermissionController
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
                                                                GetPermissionViewModel getPermissionViewModel,
                                                                GetPermissionDataAccessInterface getPermissionDataAccessInterface) {
        GetPermissionOutputBoundary getPermissionOutputBoundary = new GetPermissionPresenter(viewManagerModel, getPermissionViewModel);

        GetPermissionInputBoundary getPermissionInteractor = new GetPermissionInteractor(getPermissionOutputBoundary, getPermissionDataAccessInterface);

        return new GetPermissionController(getPermissionInteractor);
    }

    private static DeletePermissionController deletePermissionUseCase(ViewManagerModel viewManagerModel,
                                                                      GetPermissionViewModel getPermissionViewModel,
                                                                      DeletePermissionViewModel deletePermissionViewModel,
                                                                      DeletePermissionDataAccessInterface deletePermissionDataAccessInterface) {
        DeletePermissionOutputBoundary deletePermissionOutputBoundary = new DeletePermissionPresenter(viewManagerModel, getPermissionViewModel, deletePermissionViewModel);

        DeletePermissionInputBoundary deletePermissionInteractor = new DeletePermissionInteractor(deletePermissionOutputBoundary, deletePermissionDataAccessInterface);

        return new DeletePermissionController(deletePermissionInteractor);
    }
}
