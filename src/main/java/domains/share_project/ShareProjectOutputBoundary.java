package domains.share_project;

public interface ShareProjectOutputBoundary {
    void prepareSuccessView(ShareProjectOutputData shareProjectOutputData);
    void prepareFailView(Integer errorCode);
}
