package domains.share_project;

import domains.permission.entity.NewPermissionFactory;
public class ShareProjectInteractor implements ShareProjectInputBoundary {
    private final ShareProjectDataAccessInterface shareProjectDataAccessInterface;
    private final ShareProjectOutputBoundary shareProjectPresenter;

    public ShareProjectInteractor(
            ShareProjectDataAccessInterface shareProjectDataAccessInterface,
            ShareProjectOutputBoundary shareProjectPresenter
    ) {
        this.shareProjectDataAccessInterface = shareProjectDataAccessInterface;
        this.shareProjectPresenter = shareProjectPresenter;
    }
    public void execute(ShareProjectInputData shareProjectInputData) {
        try {
            shareProjectDataAccessInterface.createPermission(
                    NewPermissionFactory.create(
                            shareProjectInputData.getProjectId(),
                            shareProjectInputData.getOtherUserId(),
                            "editor",
                            "")
            );

            ShareProjectOutputData outputData = new ShareProjectOutputData(
                    shareProjectInputData.getOtherUserName(),
                    shareProjectInputData.getOtherUserId(),
                    shareProjectInputData.getProjectId()
            );
            shareProjectPresenter.prepareSuccessView(outputData);
            return;
        } catch (Exception e) {
            shareProjectPresenter.prepareFailView(1);
            return;
        }
    };
}
