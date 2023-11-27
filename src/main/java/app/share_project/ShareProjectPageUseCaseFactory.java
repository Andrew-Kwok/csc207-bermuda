package app.share_project;

import domains.project.share_project.ShareProjectDataAccessInterface;
import domains.project.share_project.ShareProjectInputBoundary;
import domains.project.share_project.ShareProjectInteractor;
import domains.project.share_project.ShareProjectOutputBoundary;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.project.share_project.ShareProjectController;
import interface_adapter.project.share_project.ShareProjectPresenter;
import interface_adapter.project.share_project.ShareProjectViewModel;
import interface_adapter.project.share_project_page.ShareProjectPageViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.project.ShareProjectPageView;

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