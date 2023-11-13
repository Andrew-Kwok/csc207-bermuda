package domains.project.use_case.share_project;

import domains.permission.use_case.*;
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

    };
}
