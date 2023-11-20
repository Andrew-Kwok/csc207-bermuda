package interface_adapter.permission.update_permission;

import domains.permission.use_case.update_permission.UpdatePermissionOutputBoundary;
import domains.permission.use_case.update_permission.UpdatePermissionOutputData;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class UpdatePermissionPresenter implements UpdatePermissionOutputBoundary {
    private final UpdatePermissionViewModel updatePermissionViewModel;
    private final GetPermissionViewModel getPermissionViewModel;

    private ViewManagerModel viewManagerModel;

    public UpdatePermissionPresenter(ViewManagerModel viewManagerModel, UpdatePermissionViewModel createPermissionViewModel, GetPermissionViewModel getPermissionViewModel) {
        this.updatePermissionViewModel = createPermissionViewModel;
        this.getPermissionViewModel = getPermissionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(UpdatePermissionOutputData updatePermissionOutputData) {
        UpdatePermissionState updatePermissionState = updatePermissionViewModel.getState();
        updatePermissionState.setPermissionId(updatePermissionOutputData.getPermissionId());
        updatePermissionViewModel.firePropertyChanged();
        getPermissionViewModel.firePropertyChanged();

        // On success, switch to the get permission view.
        viewManagerModel.setActiveView(getPermissionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        UpdatePermissionState updatePermissionState = updatePermissionViewModel.getState();
        updatePermissionState.setUpdatePermissionError(error);
        updatePermissionViewModel.firePropertyChanged();
    }
}
