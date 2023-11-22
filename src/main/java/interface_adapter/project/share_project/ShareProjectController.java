package interface_adapter.project.share_project;

import domains.share_project.ShareProjectInputBoundary;
import domains.share_project.ShareProjectInputData;
public class ShareProjectController {
    ShareProjectInputBoundary shareProjectInteractor;
    public ShareProjectController(
            ShareProjectInputBoundary shareProjectInteractor
    ) {
        this.shareProjectInteractor = shareProjectInteractor;
    }
    public void execute(
            String projectId, String userId, String otherUserName
    ) {
        shareProjectInteractor.execute(new ShareProjectInputData(
                projectId,
                userId,
                otherUserName
        ));
    }
}
