package domains.project.use_case.share_project_page;

public interface ShareProjectPageOutputBoundary {
    void prepareSuccessView(ShareProjectPageOutputData output);
    void prepareFailView(ShareProjectPageOutputData output);
}
