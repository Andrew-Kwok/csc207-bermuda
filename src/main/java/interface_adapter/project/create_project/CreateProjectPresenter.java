package interface_adapter.project.create_project;

import domains.project.use_case.create_project.CreateProjectOutputBoundary;
import domains.project.use_case.create_project.CreateProjectOutputData;
import interface_adapter.project.get_project.GetProjectState;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class CreateProjectPresenter implements CreateProjectOutputBoundary {

    private final CreateProjectViewModel createProjectViewModel;
    private final GetProjectViewModel getProjectViewModel;

    private ViewManagerModel viewManagerModel;

    public CreateProjectPresenter(ViewManagerModel viewManagerModel,
                                  CreateProjectViewModel createProjectViewModel,
                                  GetProjectViewModel getProjectViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.createProjectViewModel = createProjectViewModel;
        this.getProjectViewModel = getProjectViewModel;
    }


    @Override
    public void prepareSuccessView(CreateProjectOutputData createProjectOutputData) {
        CreateProjectState createProjectState = createProjectViewModel.getState();
        createProjectState.setProjectName(createProjectOutputData.getProjectName());
        this.createProjectViewModel.setState(createProjectState);
        createProjectViewModel.firePropertyChanged();

        GetProjectState getProjectState = getProjectViewModel.getState();
        getProjectState.setInitial(true);
        getProjectViewModel.setState(getProjectState);
        getProjectViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(getProjectViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CreateProjectState createProjectState = createProjectViewModel.getState();
        createProjectState.setProjectError(error);
        createProjectViewModel.firePropertyChanged();
    }
}

