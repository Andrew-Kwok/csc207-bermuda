package domains.project.use_case.share_project;

public interface ShareProjectOutputBoundary {
    void prepareSuccessView(ShareProjectOutputData shareProjectOutputData);
    void prepareFailView(Integer errorCode);
}
