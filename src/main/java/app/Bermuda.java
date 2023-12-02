package app;

import app.permission.CreatePermissionUseCaseFactory;
import app.permission.GetPermissionUseCaseFactory;
import app.permission.UpdatePermissionUseCaseFactory;
import app.project.CreateProjectUseCaseFactory;
import app.project.GetProjectUseCaseFactory;
import app.project.ShareProjectPageUseCaseFactory;
import app.task.AddTaskUseCaseFactory;
import app.task.EditTaskUseCaseFactory;
import app.task.GetTaskUseCaseFactory;
import app.user.LoggedInUseCaseFactory;
import app.user.LoginUseCaseFactory;
import app.user.SignupUseCaseFactory;
import config.Config;
import data_access.cloudsql.SqlConfig;
import data_access.cloudsql.SqlDataAccessObject;
import data_access.todoist.ApiDataAccessObject;
import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.permission.use_case.delete_permission.DeletePermissionDataAccessInterface;
import domains.permission.use_case.get_permission.GetPermissionDataAccessInterface;
import domains.permission.use_case.update_permission.UpdatePermissionDataAccessInterface;
import domains.project.use_case.create_project.CreateProjectApiDataAccessInterface;
import domains.project.use_case.create_project.CreateProjectSqlDataAccessInterface;
import domains.project.use_case.delete_project.DeleteProjectApiDataAccessInterface;
import domains.project.use_case.delete_project.DeleteProjectSqlDataAccessInterface;
import domains.project.use_case.get_project.GetProjectApiDataAccessInterface;
import domains.project.use_case.get_project.GetProjectSqlDataAccessInterface;
import domains.project.use_case.share_project.ShareProjectDataAccessInterface;
import domains.project.use_case.share_project_page.ShareProjectPageDataAccessInterface;
import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.close_task.CloseTaskDataAccessInterface;
import domains.task.use_case.edit_task.EditTaskDataAccessInterface;
import domains.task.use_case.get_task.GetTaskDataAccessInterface;
import domains.user.use_case.login.LoginUserDataAccessInterface;
import domains.user.use_case.signup.SignupUserDataAccessInterface;
import interface_adapter.permission.create_permission.CreatePermissionViewModel;
import interface_adapter.permission.delete_permission.DeletePermissionViewModel;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.permission.update_permission.UpdatePermissionViewModel;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.project.share_project.ShareProjectViewModel;
import interface_adapter.project.share_project_page.ShareProjectPageViewModel;
import interface_adapter.project.delete_project.DeleteProjectViewModel;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.task.close_task.CloseTaskViewModel;
import interface_adapter.task.edit_task.EditTaskViewModel;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.user.loggedin.LoggedInViewModel;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.user.signup.SignupViewModel;
import interface_adapter.view_model.ViewManagerModel;
import view.ViewManager;
import view.permission.CreatePermissionView;
import view.permission.GetPermissionView;
import view.permission.UpdatePermissionView;
import view.project.CreateProjectView;
import view.project.GetProjectView;
import view.project.ShareProjectPageView;
import view.task.AddTaskView;
import view.task.EditTaskView;
import view.task.GetTaskView;
import view.user.LoggedInView;
import view.user.LoginView;
import view.user.SignupView;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;

/**
 * Created by CSC207 project team
 * Initiate the environment and key elements to run Bermuda
 * Set up Views and Java Swring as UI
 */
public class Bermuda {
    public static void main(String[] args) {
        JFrame application = new JFrame("Bermuda Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // build the view manager and add views
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInUserViewModel = new LoggedInViewModel();

        GetPermissionViewModel getPermissionViewModel = new GetPermissionViewModel();
        CreatePermissionViewModel createPermissionViewModel = new CreatePermissionViewModel();
        UpdatePermissionViewModel updatePermissionViewModel = new UpdatePermissionViewModel();
        DeletePermissionViewModel deletePermissionViewModel = new DeletePermissionViewModel();

        CreateProjectViewModel createProjectViewModel = new CreateProjectViewModel();
        GetProjectViewModel getProjectViewModel = new GetProjectViewModel();
        DeleteProjectViewModel deleteProjectViewModel = new DeleteProjectViewModel();
        ShareProjectViewModel shareProjectViewModel = new ShareProjectViewModel();
        ShareProjectPageViewModel shareProjectPageViewModel = new ShareProjectPageViewModel();

        AddTaskViewModel addTaskViewModel = new AddTaskViewModel();
        GetTaskViewModel getTaskViewModel = new GetTaskViewModel();
        EditTaskViewModel editTaskViewModel = new EditTaskViewModel();
        CloseTaskViewModel closeTaskViewModel = new CloseTaskViewModel();

        // data access object
        DataSource sqlDataSource = SqlConfig.NewSQL();
        SqlDataAccessObject sqlDataAccessObject = new SqlDataAccessObject(sqlDataSource);

        ApiDataAccessObject apiDataAccessObject = new ApiDataAccessObject(
                Config.getEnv("TODOIST_API_URL"),
                Config.getEnv("TODOIST_API_TOKEN")
        );

        SignupUserDataAccessInterface signupUserDataAccessInterface = sqlDataAccessObject;
        LoginUserDataAccessInterface loginUserDataAccessInterface = sqlDataAccessObject;
        GetPermissionDataAccessInterface getPermissionDataAccessInterface = sqlDataAccessObject;
        CreatePermissionDataAccessInterface createPermissionDataAccessInterface = sqlDataAccessObject;
        UpdatePermissionDataAccessInterface updatePermissionDataAccessInterface = sqlDataAccessObject;
        DeletePermissionDataAccessInterface deletePermissionDataAccessInterface = sqlDataAccessObject;

        CreateProjectSqlDataAccessInterface createProjectSqlDataAccessInterface = sqlDataAccessObject;
        CreateProjectApiDataAccessInterface createProjectApiDataAccessInterface = apiDataAccessObject;
        DeleteProjectApiDataAccessInterface deleteProjectApiDataAccessInterface = apiDataAccessObject;
        DeleteProjectSqlDataAccessInterface deleteProjectSqlDataAccessInterface = sqlDataAccessObject;
        GetProjectSqlDataAccessInterface getProjectSqlDataAccessInterface = sqlDataAccessObject;
        GetProjectApiDataAccessInterface getProjectApiDataAccessInterface = apiDataAccessObject;

        ShareProjectDataAccessInterface shareProjectDataAccessInterface = sqlDataAccessObject;
        ShareProjectPageDataAccessInterface shareProjectPageDataAccessInterface = sqlDataAccessObject;

        AddTaskDataAccessInterface addTaskDataAccessInterface = apiDataAccessObject;
        GetTaskDataAccessInterface getTaskDataAccessInterface = apiDataAccessObject;
        EditTaskDataAccessInterface editTaskDataAccessInterface = apiDataAccessObject;
        CloseTaskDataAccessInterface closeTaskDataAccessInterface = apiDataAccessObject;

//        try {
//            signupUserDataAccessInterface = new FileUserDataAccessObject("./users.csv", "./projects,csv");
//            loginUserDataAccessInterface = (LoginUserDataAccessInterface) signupUserDataAccessInterface;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // create views
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, loginViewModel, loggedInUserViewModel,
                signupUserDataAccessInterface, loginUserDataAccessInterface);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInUserViewModel,
                loginUserDataAccessInterface);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInUserView = LoggedInUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInUserViewModel, getProjectViewModel, getPermissionViewModel);
        views.add(loggedInUserView, loggedInUserView.viewName);

        GetPermissionView getPermissionView = GetPermissionUseCaseFactory.create(viewManagerModel, loggedInUserViewModel,
                createPermissionViewModel, createPermissionDataAccessInterface,
                getPermissionViewModel, getPermissionDataAccessInterface,
                updatePermissionViewModel, updatePermissionDataAccessInterface,
                deletePermissionViewModel, deletePermissionDataAccessInterface);
        views.add(getPermissionView, getPermissionView.viewName);

        CreatePermissionView createPermissionView = CreatePermissionUseCaseFactory.create(viewManagerModel, createPermissionViewModel, getPermissionViewModel,
                createPermissionDataAccessInterface, getPermissionDataAccessInterface);
        views.add(createPermissionView, createPermissionView.viewName);

        UpdatePermissionView updatePermissionView = UpdatePermissionUseCaseFactory.create(viewManagerModel, updatePermissionViewModel, getPermissionViewModel,
                updatePermissionDataAccessInterface, getPermissionDataAccessInterface);
        views.add(updatePermissionView, updatePermissionView.viewName);

        CreateProjectView createProjectView = CreateProjectUseCaseFactory.create(viewManagerModel, createProjectViewModel, getProjectViewModel,
                createProjectApiDataAccessInterface, createProjectSqlDataAccessInterface);
        views.add(createProjectView, createProjectView.viewName);

        GetProjectView getProjectView = GetProjectUseCaseFactory.create(
                viewManagerModel,
                loggedInUserViewModel,
                createProjectViewModel,
                getProjectViewModel,
                deleteProjectViewModel,
                shareProjectPageViewModel,
                getTaskViewModel,
                createProjectApiDataAccessInterface,
                createProjectSqlDataAccessInterface,
                getProjectApiDataAccessInterface,
                getProjectSqlDataAccessInterface,
                deleteProjectApiDataAccessInterface,
                deleteProjectSqlDataAccessInterface,
                shareProjectPageDataAccessInterface);
        views.add(getProjectView, getProjectView.viewName);

        ShareProjectPageView shareProjectPageView = ShareProjectPageUseCaseFactory.create(
                viewManagerModel, getProjectViewModel, shareProjectPageViewModel, shareProjectViewModel,
                shareProjectDataAccessInterface);
        views.add(shareProjectPageView, shareProjectPageView.viewName);

        AddTaskView addTaskView = AddTaskUseCaseFactory.create(viewManagerModel, addTaskViewModel, getTaskViewModel,
                addTaskDataAccessInterface);
        views.add(addTaskView, addTaskView.viewName);

        GetTaskView getTaskView = GetTaskUseCaseFactory.create(viewManagerModel, addTaskViewModel, getTaskViewModel, editTaskViewModel, getProjectViewModel, closeTaskViewModel,
                addTaskDataAccessInterface, getTaskDataAccessInterface, closeTaskDataAccessInterface);
        views.add(getTaskView, getTaskView.viewName);

        EditTaskView editTaskView = EditTaskUseCaseFactory.create(viewManagerModel, editTaskViewModel, getTaskViewModel,
                editTaskDataAccessInterface, getTaskDataAccessInterface);
        views.add(editTaskView, editTaskView.viewName);

        // set the initial view
        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
