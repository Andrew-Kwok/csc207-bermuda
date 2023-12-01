package domains.project.use_case.delete_project;

public interface DeleteProjectOutputBoundary {
    void prepareSuccessView(DeleteProjectOutputData project);

    void prepareFailView(String error);
}
