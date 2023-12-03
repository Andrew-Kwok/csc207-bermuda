package interface_adapter.project.edit_project;

import domains.project.use_case.edit_project.EditProjectOutputBoundary;
import domains.project.use_case.edit_project.EditProjectOutputData;
import interface_adapter.project.get_project.GetProjectState;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.view_model.ViewManagerModel;

public class EditProjectPresenter implements EditProjectOutputBoundary {

    private final EditProjectViewModel editProjectViewModel;
    private final GetProjectViewModel getProjectViewModel;
    private ViewManagerModel viewManagerModel;

    public EditProjectPresenter(ViewManagerModel viewManagerModel,
                                EditProjectViewModel editProjectViewModel,
                                GetProjectViewModel getProjectViewModel) {
        this.editProjectViewModel = editProjectViewModel;
        this.getProjectViewModel = getProjectViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EditProjectOutputData editProjectOutputData) {
        EditProjectState editProjectState = editProjectViewModel.getState();
        editProjectState.setProjectID(editProjectOutputData.getProjectID());
        editProjectViewModel.firePropertyChanged();

        GetProjectState getProjectState = getProjectViewModel.getState();
        getProjectState.setInitial(true);
        getProjectViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(getProjectViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        EditProjectState editProjectState = editProjectViewModel.getState();
        editProjectState.setEditProjectError(error);
        editProjectViewModel.firePropertyChanged();
    }
}
