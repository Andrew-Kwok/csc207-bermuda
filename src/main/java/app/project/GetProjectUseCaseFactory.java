package app.project;

import domains.project.use_case.create_project.*;
import domains.project.use_case.delete_project.*;
import domains.project.use_case.get_project.*;
import domains.project.use_case.share_project_page.ShareProjectPageDataAccessInterface;
import domains.project.use_case.share_project_page.ShareProjectPageInputBoundary;
import domains.project.use_case.share_project_page.ShareProjectPageInteractor;
import domains.project.use_case.share_project_page.ShareProjectPageOutputBoundary;
import interface_adapter.project.create_project.CreateProjectController;
import interface_adapter.project.create_project.CreateProjectPresenter;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.project.delete_project.DeleteProjectController;
import interface_adapter.project.delete_project.DeleteProjectPresenter;
import interface_adapter.project.delete_project.DeleteProjectViewModel;
import interface_adapter.project.edit_project.EditProjectViewModel;
import interface_adapter.project.get_project.GetProjectController;
import interface_adapter.project.get_project.GetProjectPresenter;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.project.share_project_page.ShareProjectPageController;
import interface_adapter.project.share_project_page.ShareProjectPagePresenter;
import interface_adapter.project.share_project_page.ShareProjectPageViewModel;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.user.loggedin.LoggedInViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.project.GetProjectView;

public class GetProjectUseCaseFactory {
    private GetProjectUseCaseFactory() {}

    public static GetProjectView create(ViewManagerModel viewManagerModel,
                                        LoggedInViewModel loggedInViewModel,
                                        CreateProjectViewModel createProjectViewModel,
                                        GetProjectViewModel getProjectViewModel,
                                        DeleteProjectViewModel deleteProjectViewModel,
                                        ShareProjectPageViewModel shareProjectPageViewModel,
                                        EditProjectViewModel editProjectViewModel,
                                        GetTaskViewModel getTaskViewModel,
                                        CreateProjectApiDataAccessInterface createProjectApiDAI,
                                        CreateProjectSqlDataAccessInterface createProjectSqlDAI,
                                        GetProjectApiDataAccessInterface getProjectApiDAI,
                                        GetProjectSqlDataAccessInterface getProjectSqlDAI,
                                        DeleteProjectApiDataAccessInterface deleteProjectApiDAI,
                                        DeleteProjectSqlDataAccessInterface deleteProjectSqlDAI,
                                        ShareProjectPageDataAccessInterface shareProjectPageDAI
    ) {
        CreateProjectController createProjectController = createProjectUseCase(
                viewManagerModel,
                createProjectViewModel,
                getProjectViewModel,
                createProjectSqlDAI,
                createProjectApiDAI
        );
        GetProjectController getProjectController = getProjectUseCase(
                viewManagerModel,
                getProjectViewModel,
                getProjectSqlDAI,
                getProjectApiDAI
        );
        DeleteProjectController deleteProjectController = deleteProjectUseCase(
                viewManagerModel,
                getProjectViewModel,
                deleteProjectViewModel,
                deleteProjectApiDAI,
                deleteProjectSqlDAI
        );
        ShareProjectPageController shareProjectPageController = shareProjectPageUseCase(
                viewManagerModel,
                shareProjectPageViewModel,
                shareProjectPageDAI
        );

        return new GetProjectView(
                viewManagerModel,
                loggedInViewModel,
                getProjectViewModel,
                getProjectController,
                createProjectViewModel,
                createProjectController,
                deleteProjectViewModel,
                deleteProjectController,
                shareProjectPageViewModel,
                shareProjectPageController,
                editProjectViewModel,
                getTaskViewModel
        );
    }

    private static CreateProjectController createProjectUseCase(ViewManagerModel viewManagerModel,
                                                                CreateProjectViewModel createProjectViewModel,
                                                                GetProjectViewModel getProjectViewModel,
                                                                CreateProjectSqlDataAccessInterface createProjectSqlDAI,
                                                                CreateProjectApiDataAccessInterface createProjectApiDAI) {
        CreateProjectOutputBoundary createProjectOutputBoundary = new CreateProjectPresenter(
                viewManagerModel,
                createProjectViewModel,
                getProjectViewModel
        );

        CreateProjectInputBoundary createProjectInteractor = new CreateProjectInteractor(
                createProjectOutputBoundary,
                createProjectApiDAI,
                createProjectSqlDAI
        );

        return new CreateProjectController(createProjectInteractor);

    }

    private static GetProjectController getProjectUseCase(ViewManagerModel viewManagerModel,
                                                          GetProjectViewModel getProjectViewModel,
                                                          GetProjectSqlDataAccessInterface getProjectSqlDAI,
                                                          GetProjectApiDataAccessInterface getProjectApiDAI) {
        GetProjectOutputBoundary getProjectOutputBoundary = new GetProjectPresenter(
                viewManagerModel,
                getProjectViewModel
        );

        GetProjectInputBoundary getProjectInteractor = new GetProjectInteractor(
                getProjectOutputBoundary,
                getProjectSqlDAI,
                getProjectApiDAI
        );

        return new GetProjectController(getProjectInteractor);
    }

    private static DeleteProjectController deleteProjectUseCase(ViewManagerModel viewManagerModel,
                                                                   GetProjectViewModel getProjectViewModel,
                                                                   DeleteProjectViewModel deleteProjectViewModel,
                                                                   DeleteProjectApiDataAccessInterface deleteProjectApiDAI,
                                                                   DeleteProjectSqlDataAccessInterface deleteProjectSqlDAI) {
        DeleteProjectOutputBoundary deleteProjectOutputBoundary = new DeleteProjectPresenter(
                viewManagerModel,
                getProjectViewModel,
                deleteProjectViewModel
        );

        DeleteProjectInputBoundary deleteProjectInteractor = new DeleteProjectInteractor(
                deleteProjectOutputBoundary,
                deleteProjectApiDAI,
                deleteProjectSqlDAI
        );

        return new DeleteProjectController(deleteProjectInteractor);
    }

    private static ShareProjectPageController shareProjectPageUseCase(ViewManagerModel viewManagerModel,
                                                                      ShareProjectPageViewModel shareProjectPageViewModel,
                                                                      ShareProjectPageDataAccessInterface shareProjectPageDAI) {
        ShareProjectPageOutputBoundary shareProjectPageOutputBoundary = new ShareProjectPagePresenter(
                viewManagerModel,
                shareProjectPageViewModel
        );

        ShareProjectPageInputBoundary shareProjectPageInteractor = new ShareProjectPageInteractor(
                shareProjectPageOutputBoundary,
                shareProjectPageDAI
        );

        return new ShareProjectPageController(shareProjectPageInteractor);
    }
}
