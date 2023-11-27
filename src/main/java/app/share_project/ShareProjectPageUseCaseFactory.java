package app.share_project;

import domains.share_project.ShareProjectDataAccessInterface;
import domains.share_project.ShareProjectInputBoundary;
import domains.share_project.ShareProjectInteractor;
import domains.share_project.ShareProjectOutputBoundary;

import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.share_project.ShareProjectController;
import interface_adapter.share_project.ShareProjectPresenter;
import interface_adapter.share_project.ShareProjectViewModel;
import interface_adapter.share_project.share_project_page.ShareProjectPageViewModel;
import interface_adapter.view_model.ViewManagerModel;

import view.share_project.ShareProjectPageView;

public class ShareProjectPageUseCaseFactory {
    private ShareProjectPageUseCaseFactory() {}

    public static ShareProjectPageView create(
            ViewManagerModel viewManagerModel,
            GetProjectViewModel getProjectViewModel,
            ShareProjectPageViewModel shareProjectPageViewModel,
            ShareProjectViewModel shareProjectViewModel,
            ShareProjectDataAccessInterface shareProjectDataAccessInterface
            ) {
        ShareProjectController shareProjectController = shareProjectUseCase(
                viewManagerModel,
                shareProjectViewModel,
                shareProjectDataAccessInterface
        );

        return new ShareProjectPageView(
                viewManagerModel,
                getProjectViewModel,
                shareProjectPageViewModel,
                shareProjectViewModel,
                shareProjectController
        );
    }

    private static ShareProjectController shareProjectUseCase(ViewManagerModel viewManagerModel,
                                                              ShareProjectViewModel shareProjectViewModel,
                                                              ShareProjectDataAccessInterface shareProjectDataAccessInterface
    ) {
        ShareProjectOutputBoundary presenter = new ShareProjectPresenter(viewManagerModel, shareProjectViewModel);

        ShareProjectInputBoundary interactor = new ShareProjectInteractor(
                shareProjectDataAccessInterface,
                presenter
        );
        return new ShareProjectController(interactor);
    }
}