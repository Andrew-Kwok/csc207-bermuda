package interface_adapter.project.create_project;

import domains.project.use_case.create_project.CreateProjectDataAccessInterface;
import domains.project.use_case.create_project.CreateProjectInputData;
import domains.project.use_case.create_project.CreateProjectOutputBoundary;
import interface_adapter.view_model.ViewManagerModel;

public class CreateProjectPresenter implements CreateProjectOutputBoundary {

    private final CreateProjectViewModel createProjectViewModel;

    private final CreateProjectDataAccessInterface createProject;
    private ViewManagerModel viewManagerModel;

    public CreateProjectPresenter(ViewManagerModel viewManagerModel,
                            CreateProjectViewModel createProjectViewModel,
                            CreateProjectDataAccessInterface createProject){

        this.viewManagerModel = viewManagerModel;
        this.createProjectViewModel = createProjectViewModel;
        this.createProject = createProject;
    }


    @Override
    public void prepareSuccessView(String projectName) {
        CreateProjectInputData createProjectInputdata = new CreateProjectInputData(projectName);
        CreateProjectState createProjectState = createProjectViewModel.getState();
        createProjectState.setProjectName(createProjectInputdata.getName());
        this.createProjectViewModel.setState(createProjectState);
        createProjectViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CreateProjectState createProjectState = createProjectViewModel.getState();
        createProjectState.setProjectError(error);
        createProjectViewModel.firePropertyChanged();
    }

}

