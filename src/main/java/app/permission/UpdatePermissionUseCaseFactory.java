package app.permission;

import domains.permission.entity.NewPermissionFactory;
import domains.permission.use_case.update_permission.UpdatePermissionDataAccessInterface;
import domains.permission.use_case.update_permission.UpdatePermissionInputBoundary;
import domains.permission.use_case.update_permission.UpdatePermissionInteractor;
import domains.permission.use_case.update_permission.UpdatePermissionOutputBoundary;
import domains.permission.use_case.get_permission.GetPermissionDataAccessInterface;
import domains.permission.use_case.get_permission.GetPermissionInputBoundary;
import domains.permission.use_case.get_permission.GetPermissionInteractor;
import domains.permission.use_case.get_permission.GetPermissionOutputBoundary;
import interface_adapter.permission.update_permission.UpdatePermissionController;
import interface_adapter.permission.update_permission.UpdatePermissionPresenter;
import interface_adapter.permission.update_permission.UpdatePermissionViewModel;
import interface_adapter.permission.get_permission.GetPermissionController;
import interface_adapter.permission.get_permission.GetPermissionPresenter;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.permission.UpdatePermissionView;

public class UpdatePermissionUseCaseFactory {
    private UpdatePermissionUseCaseFactory() {}

    public static UpdatePermissionView create(ViewManagerModel viewManagerModel,
                                              UpdatePermissionViewModel updatePermissionViewModel, GetPermissionViewModel getPermissionViewModel,
                                              UpdatePermissionDataAccessInterface updatePermissionDataAccessInterface, GetPermissionDataAccessInterface getPermissionDataAccessInterface) {
        UpdatePermissionController updatePermissionController = updatePermissionUseCase(
                viewManagerModel, updatePermissionViewModel, getPermissionViewModel, updatePermissionDataAccessInterface
        );
        GetPermissionController getPermissionController = getPermissionUseCase(
                viewManagerModel, getPermissionViewModel, getPermissionDataAccessInterface
        );

        return new UpdatePermissionView(
                updatePermissionController, updatePermissionViewModel, getPermissionController, getPermissionViewModel, viewManagerModel
        );
    }

    private static UpdatePermissionController updatePermissionUseCase(ViewManagerModel viewManagerModel,
                                                                      UpdatePermissionViewModel updatePermissionViewModel, GetPermissionViewModel getPermissionViewModel,
                                                                      UpdatePermissionDataAccessInterface updatePermissionDataAccessInterface) {
        UpdatePermissionOutputBoundary updatePermissionOutputBoundary = new UpdatePermissionPresenter(viewManagerModel, updatePermissionViewModel, getPermissionViewModel);

        UpdatePermissionInputBoundary updatePermissionInteractor = new UpdatePermissionInteractor(updatePermissionOutputBoundary, updatePermissionDataAccessInterface);

        return new UpdatePermissionController(updatePermissionInteractor);
    }

    private static GetPermissionController getPermissionUseCase(ViewManagerModel viewManagerModel,
                                                                GetPermissionViewModel getPermissionViewModel,
                                                                GetPermissionDataAccessInterface getPermissionDataAccessInterface) {
        GetPermissionOutputBoundary getPermissionOutputBoundary = new GetPermissionPresenter(viewManagerModel, getPermissionViewModel);

        GetPermissionInputBoundary getPermissionInteractor = new GetPermissionInteractor(getPermissionOutputBoundary, getPermissionDataAccessInterface);

        return new GetPermissionController(getPermissionInteractor);
    }
}
