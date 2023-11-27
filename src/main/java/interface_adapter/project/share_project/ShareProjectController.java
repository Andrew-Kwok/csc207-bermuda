package interface_adapter.project.share_project;

import domains.project.share_project.ShareProjectInputBoundary;
import domains.project.share_project.ShareProjectInputData;
public class ShareProjectController {
    ShareProjectInputBoundary shareProjectInteractor;
    public ShareProjectController(
            ShareProjectInputBoundary shareProjectInteractor
    ) {
        this.shareProjectInteractor = shareProjectInteractor;
    }
    public void execute(String projectId, String otherUserId, String otherUserName) {
        shareProjectInteractor.execute(new ShareProjectInputData(
                projectId, otherUserId, otherUserName
        ));
    }
}
