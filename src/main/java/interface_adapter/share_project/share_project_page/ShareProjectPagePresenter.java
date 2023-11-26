package interface_adapter.share_project.share_project_page;


import domains.share_project.share_project_page.ShareProjectPageOutputBoundary;
import domains.share_project.share_project_page.ShareProjectPageOutputData;
import interface_adapter.view_model.ViewManagerModel;
public class ShareProjectPagePresenter implements ShareProjectPageOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ShareProjectPageViewModel shareProjectPageViewModel;
    private final ShareProjectPageState state = new ShareProjectPageState();
    public ShareProjectPagePresenter(
            ViewManagerModel viewManagerModel,
            ShareProjectPageViewModel shareProjectPageViewModel
    ) {
        this.shareProjectPageViewModel = shareProjectPageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(ShareProjectPageOutputData output) {
        shareProjectPageViewModel.setState(
                ShareProjectPageState.newBuilder()
                        .setProjectId(output.getProjectId())
                        .setProjectName(output.getProjectName())
                        .setUsersNameAndId(output.getUsersNameAndId())
                        .build()
        );

        shareProjectPageViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(shareProjectPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(ShareProjectPageOutputData output) {
        if (output.getErrorCode() == 1) {
            shareProjectPageViewModel.setState(
                    ShareProjectPageState.newBuilder()
                            .setErrorMessage("Internal server error, error code 1.")
                            .build()
            );
        } else {
            shareProjectPageViewModel.setState(
                    ShareProjectPageState.newBuilder()
                            .setErrorMessage("Unknown error cause, error code 2.")
                            .build()
            );
        }

        shareProjectPageViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(shareProjectPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
