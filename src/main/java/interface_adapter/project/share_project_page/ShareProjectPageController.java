package interface_adapter.project.share_project_page;

import domains.project.use_case.share_project_page.ShareProjectPageInputBoundary;
import domains.project.use_case.share_project_page.ShareProjectPageInputData;
public class ShareProjectPageController {
    private final ShareProjectPageInputBoundary shareProjectInteractor;

    public ShareProjectPageController(ShareProjectPageInputBoundary shareProjectInteractor) {
        this.shareProjectInteractor = shareProjectInteractor;
    }

    public void execute(String projectId, String projectName, String userId) {
        shareProjectInteractor.execute(new ShareProjectPageInputData(userId, projectId, projectName));
    }
}
