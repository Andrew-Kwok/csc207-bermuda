package interface_adapter.share_project.share_project_page;

import domains.share_project.share_project_page.ShareProjectPageInputBoundary;
import domains.share_project.share_project_page.ShareProjectPageInputData;
public class ShareProjectPageController {
    private final ShareProjectPageInputBoundary shareProjectInteractor;

    public ShareProjectPageController(ShareProjectPageInputBoundary shareProjectInteractor) {
        this.shareProjectInteractor = shareProjectInteractor;
    }

    public void execute(String projectId, String projectName, String userId) {
        shareProjectInteractor.execute(new ShareProjectPageInputData(userId, projectId, projectName));
    }
}
