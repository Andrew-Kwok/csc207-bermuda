package interface_adapter.project.create_project;

import domains.project.use_case.create_project.CreateProjectDataAccessInterface;
import domains.project.use_case.create_project.CreateProjectOutputBoundary;
import domains.project.use_case.create_project.CreateProjectOutputData;
import interface_adapter.view_model.ViewManagerModel;

public class CreateProjectPresenter implements CreateProjectOutputBoundary {

    private final CreateProjectViewModel createProjectViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateProjectPresenter(ViewManagerModel viewManagerModel,
                            CreateProjectViewModel createProjectViewModel,
                            CreateProjectDataAccessInterface createProjectDAO){

        this.viewManagerModel = viewManagerModel;
        this.createProjectViewModel = createProjectViewModel;
    }

    @Override
    public void printProjectID(CreateProjectOutputData project) {
        String projectID = project.getProjectID();
        System.out.println(projectID);
    }
}
