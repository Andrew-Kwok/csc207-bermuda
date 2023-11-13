package domains.project.use_case.share_project;

public interface ShareProjectOutputBoundary {
    void prepareSuccessView();
    void prepareFailView(Integer errorCode);
}
