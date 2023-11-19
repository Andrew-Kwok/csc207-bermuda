package interface_adapter.permission.delete_permission;

import domains.permission.use_case.delete_permission.DeletePermissionOutputBoundary;
import domains.permission.use_case.delete_permission.DeletePermissionOutputData;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class DeletePermissionPresenter implements DeletePermissionOutputBoundary {
    private final GetPermissionViewModel getPermissionViewModel;
    private final DeletePermissionViewModel deletePermissionViewModel;
    private final ViewManagerModel viewManagerModel;

    public DeletePermissionPresenter(ViewManagerModel viewManagerModel, GetPermissionViewModel getPermissionViewModel, DeletePermissionViewModel deletePermissionViewModel) {
        this.getPermissionViewModel = getPermissionViewModel;
        this.deletePermissionViewModel = deletePermissionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DeletePermissionOutputData permission) {
        DeletePermissionState deletePermissionState = deletePermissionViewModel.getState();

        deletePermissionState.setPermissionId(permission.getPermissionId());
        deletePermissionViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        DeletePermissionState deletePermissionState = deletePermissionViewModel.getState();

        deletePermissionState.setDeletePermissionError(error);
        deletePermissionViewModel.firePropertyChanged();
    }
}
