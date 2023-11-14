package interface_adapter.project.share_project;

import domains.project.use_case.share_project.ShareProjectInputBoundary;
import domains.project.use_case.share_project.ShareProjectInputData;
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
