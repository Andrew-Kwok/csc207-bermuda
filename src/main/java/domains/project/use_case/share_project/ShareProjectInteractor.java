package domains.project.use_case.share_project;

import domains.permission.entity.NewPermissionFactory;
import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.project.use_case.create_project.CreateProjectInteractor;

public class ShareProjectInteractor implements ShareProjectInputBoundary {
    private final ShareProjectDataAccessInterface shareProjectDataAccessInterface;
    private final CreatePermissionDataAccessInterface createPermissionDataAccessInterface;
    private final ShareProjectOutputBoundary shareProjectPresenter;

    public ShareProjectInteractor(
            ShareProjectDataAccessInterface shareProjectDataAccessInterface,
            CreatePermissionDataAccessInterface createPermissionDataAccessInterface,
            ShareProjectOutputBoundary shareProjectPresenter
    ) {
        this.shareProjectDataAccessInterface = shareProjectDataAccessInterface;
        this.createPermissionDataAccessInterface = createPermissionDataAccessInterface;
        this.shareProjectPresenter = shareProjectPresenter;
    }
    public void execute(ShareProjectInputData shareProjectInputData) {
        try {
            shareProjectDataAccessInterface.createPermission(
                    NewPermissionFactory.create(
                            shareProjectInputData.getProjectId(),
                            shareProjectInputData.getUserId(),
                            "editor",
                            "")
            );

            ShareProjectOutputData outputData = new ShareProjectOutputData(
                    shareProjectInputData.getProjectId(),
                    shareProjectInputData.getOtherUserName()
            );
            shareProjectPresenter.prepareSuccessView(outputData);
            return;
        } catch (Exception e) {
            shareProjectPresenter.prepareFailView(1);
            return;
        }
    };
}
