package interface_adapter.project.get_project;

import domains.project.use_case.get_project.GetProjectOutputBoundary;
import domains.project.use_case.get_project.GetProjectOutputData;

import interface_adapter.view_model.ViewManagerModel;
public class GetProjectPresenter implements GetProjectOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final GetProjectViewModel getProjectViewModel;
    public GetProjectPresenter(
            ViewManagerModel viewManagerModel,
            GetProjectViewModel getProjectViewModel
    ) {
        this.viewManagerModel = viewManagerModel;
        this.getProjectViewModel = getProjectViewModel;
    }
    @Override
    public void prepareSuccessView(GetProjectOutputData getProjectOutputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
