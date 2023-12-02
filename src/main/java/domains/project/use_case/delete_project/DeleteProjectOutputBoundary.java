package domains.project.use_case.delete_project;

public interface DeleteProjectOutputBoundary {
    void prepareSuccessView(DeleteProjectOutputData projectId);

    void prepareFailView(String error);
}
