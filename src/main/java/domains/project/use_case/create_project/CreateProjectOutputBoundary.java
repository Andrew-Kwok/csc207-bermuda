package domains.project.use_case.create_project;

public interface CreateProjectOutputBoundary {

    void prepareSuccessView(CreateProjectOutputData createProjectOutputData);

    void prepareFailView(String error);
}
