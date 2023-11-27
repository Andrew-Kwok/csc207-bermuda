package domains.project.share_project;

public interface ShareProjectOutputBoundary {
    void prepareSuccessView(ShareProjectOutputData outputData);
    void prepareFailView(Integer errorCode);
}
