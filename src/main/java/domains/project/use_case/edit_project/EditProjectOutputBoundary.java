package domains.project.use_case.edit_project;

public interface EditProjectOutputBoundary {
    void prepareSuccessView(EditProjectOutputData editProjectOutputData);

    void prepareFailView(String error);
}
