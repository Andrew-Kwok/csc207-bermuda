package domains.project.share_project_page;

public interface ShareProjectPageOutputBoundary {
    void prepareSuccessView(ShareProjectPageOutputData output);
    void prepareFailView(ShareProjectPageOutputData output);
}
