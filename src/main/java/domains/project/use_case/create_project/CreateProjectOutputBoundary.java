package domains.project.use_case.create_project;

public interface CreateProjectOutputBoundary {
    void prepareSuccessView();
    void prepareSuccessView(CreateProjectOutputData createProjectOutputData);

    void prepareFailView();
    void prepareFailView(CreateProjectOutputData createProjectOutputData);
}
