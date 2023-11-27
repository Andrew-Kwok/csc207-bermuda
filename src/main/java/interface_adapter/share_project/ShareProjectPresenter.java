package interface_adapter.share_project;

import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.view_model.ViewManagerModel;

import domains.share_project.ShareProjectOutputBoundary;
import domains.share_project.ShareProjectOutputData;

public class ShareProjectPresenter implements ShareProjectOutputBoundary {
    private final ShareProjectViewModel shareProjectViewModel;
    private final ViewManagerModel viewManagerModel;
    public ShareProjectPresenter(
            ViewManagerModel viewManagerModel,
            ShareProjectViewModel shareProjectViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.shareProjectViewModel = shareProjectViewModel;
    }

    @Override
    public void prepareSuccessView(ShareProjectOutputData outputData) {
        ShareProjectState state = shareProjectViewModel.getState();

        state.setProjectId(outputData.getProjectId());
        state.setOtherUserId(outputData.getOtherUserId());
        state.setOtherUserName(outputData.getOtherUserName());

        shareProjectViewModel.setState(state);
        shareProjectViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(shareProjectViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView(Integer errorCode) {
        ShareProjectState errState = new ShareProjectState();
        errState.setErrorMessage("Internal server error, error code " + errorCode.toString() + ".");
        shareProjectViewModel.setState(errState);
        shareProjectViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(shareProjectViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
