package app.project;

import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.share_project.ShareProjectDataAccessInterface;
import domains.share_project.ShareProjectInputBoundary;
import domains.share_project.ShareProjectInteractor;
import domains.share_project.ShareProjectOutputBoundary;

import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.project.share_project.ShareProjectController;
import interface_adapter.project.share_project.ShareProjectPresenter;
import interface_adapter.project.share_project.ShareProjectViewModel;
import interface_adapter.view_model.ViewManagerModel;

import view.project.ShareProjectView;

public class ShareProjectUseCaseFactory {
    private ShareProjectUseCaseFactory() {}

    public static ShareProjectView create(
            ViewManagerModel viewManagerModel,
            GetProjectViewModel getProjectViewModel,
            ShareProjectViewModel shareProjectViewModel,
            ShareProjectDataAccessInterface shareProjectDataAccessInterface,
            CreatePermissionDataAccessInterface createPermissionDataAccessInterface
            ) {
        ShareProjectController shareProjectController = shareProjectUseCase(
                viewManagerModel,
                shareProjectViewModel,
                shareProjectDataAccessInterface,
                createPermissionDataAccessInterface
        );

        return new ShareProjectView(
                viewManagerModel,
                getProjectViewModel,
                shareProjectViewModel,
                shareProjectController
        );
    }

    private static ShareProjectController shareProjectUseCase(ViewManagerModel viewManagerModel,
                                                              ShareProjectViewModel shareProjectViewModel,
                                                              ShareProjectDataAccessInterface shareProjectDataAccessInterface,
                                                              CreatePermissionDataAccessInterface createPermissionDataAccessInterface
    ) {
        ShareProjectOutputBoundary presenter = new ShareProjectPresenter(viewManagerModel, shareProjectViewModel);

        ShareProjectInputBoundary interactor = new ShareProjectInteractor(
                shareProjectDataAccessInterface,
                createPermissionDataAccessInterface,
                presenter
        );
        return new ShareProjectController(interactor);
    }
}