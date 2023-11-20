package domains.project.use_case.get_project;

public interface GetProjectOutputBoundary {
    void prepareSuccessView(GetProjectOutputData project);
    void prepareFailView(String error);
}