package interface_adapter.project.get_project;

import domains.project.use_case.get_project.GetProjectOutputBoundary;
import domains.project.use_case.get_project.GetProjectOutputData;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class GetProjectPresenter implements GetProjectOutputBoundary {
    private final CreateProjectViewModel createProjectViewModel;
    private final GetProjectViewModel getProjectViewModel;
    private final ViewManagerModel viewManagerModel;

    public GetProjectPresenter(
            ViewManagerModel viewManagerModel,
            CreateProjectViewModel createProjectViewModel,
            GetProjectViewModel getProjectViewModel) {
        this.createProjectViewModel = createProjectViewModel;
        this.getProjectViewModel = getProjectViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(GetProjectOutputData getProjectOutputData) {
        GetProjectState getProjectState = getProjectViewModel.getState();
        getProjectState.setProjects(getProjectOutputData.getProjects());
        getProjectViewModel.setState(getProjectState);
        getProjectViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error){
        GetProjectState getProjectState = getProjectViewModel.getState();
        getProjectState.setGetProjectError(error);
        getProjectViewModel.firePropertyChanged();
    }

}
