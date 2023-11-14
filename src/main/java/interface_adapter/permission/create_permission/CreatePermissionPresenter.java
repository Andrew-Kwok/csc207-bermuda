package interface_adapter.permission.create_permission;

import domains.permission.use_case.create_permission.CreatePermissionOutputBoundary;
import domains.permission.use_case.create_permission.CreatePermissionOutputData;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class CreatePermissionPresenter implements CreatePermissionOutputBoundary {
    private final CreatePermissionViewModel createPermissionViewModel;
    private final GetPermissionViewModel getPermissionViewModel;

    private ViewManagerModel viewManagerModel;

    public CreatePermissionPresenter(ViewManagerModel viewManagerModel, CreatePermissionViewModel createPermissionViewModel, GetPermissionViewModel getPermissionViewModel) {
        this.createPermissionViewModel = createPermissionViewModel;
        this.getPermissionViewModel = getPermissionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(CreatePermissionOutputData createPermissionOutputData) {
        CreatePermissionState createPermissionState = createPermissionViewModel.getState();
        createPermissionState.setPermissionId(createPermissionOutputData.getPermissionId());
        createPermissionViewModel.firePropertyChanged();

        // On success, switch to the get permission view.
        viewManagerModel.setActiveView(getPermissionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CreatePermissionState createPermissionState = createPermissionViewModel.getState();
        createPermissionState.setCreatePermissionError(error);
        createPermissionViewModel.firePropertyChanged();
    }
}
