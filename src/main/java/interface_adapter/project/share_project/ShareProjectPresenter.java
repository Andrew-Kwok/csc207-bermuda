package interface_adapter.project.share_project;

import interface_adapter.view_model.ViewManagerModel;

import domains.share_project.ShareProjectOutputBoundary;
import domains.share_project.ShareProjectOutputData;

public class ShareProjectPresenter implements ShareProjectOutputBoundary {
    private final ShareProjectViewModel shareProjectViewModel;
    private ViewManagerModel viewManagerModel;
    public ShareProjectPresenter(
            ViewManagerModel viewManagerModel,
            ShareProjectViewModel shareProjectViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.shareProjectViewModel = shareProjectViewModel;
    }

    @Override
    public void prepareSuccessView(ShareProjectOutputData shareProjectOutputData) {

    }
    @Override
    public void prepareFailView(Integer errorCode) {

    }
}
