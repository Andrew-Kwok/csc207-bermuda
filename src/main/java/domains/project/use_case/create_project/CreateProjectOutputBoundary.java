package domains.project.use_case.create_project;

public interface CreateProjectOutputBoundary {

    void prepareSuccessView(String projectName);

    void prepareFailView(String error);
}
