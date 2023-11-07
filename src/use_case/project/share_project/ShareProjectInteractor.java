package use_case.project.share_project;

import interface_adapter.project.share_project.ShareProjectPresenter;
import data_access.
public class ShareProjectInteractor implements ShareProjectInputBoundary {
    private final ShareProjectOutputBoundary sharePresenter;
    public ShareProjectInteractor(ShareProjectOutputBoundary sharePresenter) {
        this.sharePresenter = sharePresenter;
    }
    public void execute(ShareProjectOutputData shareProjectOutputData) {
        sharePresenter.execute(shareProjectOutputData);
    }
}