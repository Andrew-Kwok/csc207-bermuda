package interface_adapter.project.delete_project;

import domains.project.use_case.delete_project.DeleteProjectOutputBoundary;
import domains.project.use_case.delete_project.DeleteProjectOutputData;
import interface_adapter.project.get_project.GetProjectState;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class DeleteProjectPresenter implements DeleteProjectOutputBoundary {
    private final GetProjectViewModel getProjectViewModel;
    private final DeleteProjectViewModel deleteProjectViewModel;
    private final ViewManagerModel viewManagerModel;

    public DeleteProjectPresenter(ViewManagerModel viewManagerModel,
                                  GetProjectViewModel getProjectView,
                                  DeleteProjectViewModel deleteProjectViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.deleteProjectViewModel = deleteProjectViewModel;
        this.getProjectViewModel = getProjectView;
    }

    @Override
    public void prepareSuccessView(DeleteProjectOutputData project) {
        DeleteProjectState deleteProjectState = deleteProjectViewModel.getState();

        GetProjectState getProjectState = getProjectViewModel.getState();
        getProjectState.setInitial(true);
        getProjectViewModel.setState(getProjectState);
        getProjectViewModel.firePropertyChanged();

        deleteProjectState.setProjectId(project.getProjectId());
        deleteProjectViewModel.firePropertyChanged();
    }
    @Override
    public void prepareFailView(String error) {
        DeleteProjectState deleteProjectState = deleteProjectViewModel.getState();

        deleteProjectState.setDeleteProjectError(error);
        deleteProjectViewModel.firePropertyChanged();
    }
}