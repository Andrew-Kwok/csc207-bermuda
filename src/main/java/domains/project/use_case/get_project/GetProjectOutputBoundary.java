package domains.project.use_case.get_project;

public interface GetProjectOutputBoundary {
    void prepareSuccessView(GetProjectOutputData getProjectOutputData);
    void prepareFailView(String error);
}
