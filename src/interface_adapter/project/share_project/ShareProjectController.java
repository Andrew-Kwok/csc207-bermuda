package interface_adapter.project.share_project;

import use_case.project.share_project.ShareProjectInputBoundary;
import use_case.project.share_project.ShareProjectInputData;

public class ShareProjectController {
    private final ShareProjectInputBoundary shareProjectInteractor;

    public ShareProjectController(ShareProjectInputBoundary shareProjectInteractor) {
        this.shareProjectInteractor = shareProjectInteractor;
    }

    public void execute() {
        ShareProjectInputData shareProjectInputData;
        shareProjectInteractor.execute(shareProjectInputData);
    }
}
