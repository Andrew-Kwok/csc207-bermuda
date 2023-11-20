package interface_adapter.permission.get_permission;

import domains.permission.use_case.get_permission.GetPermissionOutputBoundary;
import domains.permission.use_case.get_permission.GetPermissionOutputData;
import interface_adapter.permission.create_permission.CreatePermissionViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class GetPermissionPresenter implements GetPermissionOutputBoundary {
    private final GetPermissionViewModel getPermissionViewModel;
    private final ViewManagerModel viewManagerModel;

    public GetPermissionPresenter(ViewManagerModel viewManagerModel, GetPermissionViewModel getPermissionViewModel) {
        this.getPermissionViewModel = getPermissionViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    public void prepareSuccessView(GetPermissionOutputData getPermissionOutputData) {
        GetPermissionState getPermissionState = getPermissionViewModel.getState();
        getPermissionState.setPermissions(getPermissionOutputData.getPermissions());
        getPermissionViewModel.setState(getPermissionState);
        getPermissionViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        GetPermissionState getPermissionState = getPermissionViewModel.getState();
        getPermissionState.setGetPermissionError(error);
        getPermissionViewModel.firePropertyChanged();
    }
}
