package app;

import app.permission.CreatePermissionUseCaseFactory;
import app.permission.GetPermissionUseCaseFactory;
import app.permission.UpdatePermissionUseCaseFactory;
import app.project.CreateProjectUseCaseFactory;
import app.project.ShareProjectUseCaseFactory;
import app.task.AddTaskUseCaseFactory;
import app.user.LoggedInUseCaseFactory;
import app.user.LoginUseCaseFactory;
import app.user.SignupUseCaseFactory;

import data_access.cloudsql.SqlConfig;
import data_access.cloudsql.SqlDataAccessObject;
import data_access.todoist.ApiDataAccessObject;

import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.permission.use_case.delete_permission.DeletePermissionDataAccessInterface;
import domains.permission.use_case.get_permission.GetPermissionDataAccessInterface;
import domains.permission.use_case.update_permission.UpdatePermissionDataAccessInterface;
import domains.project.use_case.create_project.CreateProjectApiDataAccessInterface;
import domains.project.use_case.create_project.CreateProjectSqlDataAccessInterface;
import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.project.use_case.share_project.ShareProjectDataAccessInterface;
import domains.user.use_case.login.LoginUserDataAccessInterface;
import domains.user.use_case.signup.SignupUserDataAccessInterface;

import interface_adapter.permission.create_permission.CreatePermissionViewModel;
import interface_adapter.permission.delete_permission.DeletePermissionViewModel;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.permission.update_permission.UpdatePermissionViewModel;
import interface_adapter.project.create_project.CreateProjectViewModel;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.project.share_project.ShareProjectViewModel;
import interface_adapter.user.loggedin_user.LoggedInViewModel;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.user.signup.SignupViewModel;
import interface_adapter.view_model.ViewManagerModel;

import view.ViewManager;
import view.permission.CreatePermissionView;
import view.permission.GetPermissionView;
import view.permission.UpdatePermissionView;
import view.project.CreateProjectView;
import view.task.AddTaskView;
import view.project.ShareProjectView;
import view.user.LoggedInView;
import view.user.LoginView;
import view.user.SignupView;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;

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
        AddTaskViewModel addTaskViewModel = new AddTaskViewModel();
        GetTaskViewModel getTaskViewModel = new GetTaskViewModel();
        ShareProjectViewModel shareProjectViewModel = new ShareProjectViewModel();

        // data access object
        DataSource sqlDataSource = SqlConfig.NewSQL();
        SqlDataAccessObject sqlDataAccessObject = new SqlDataAccessObject(sqlDataSource);

        ApiDataAccessObject apiDataAccessObject = new ApiDataAccessObject();

        SignupUserDataAccessInterface signupUserDataAccessInterface = sqlDataAccessObject;
        LoginUserDataAccessInterface loginUserDataAccessInterface = sqlDataAccessObject;
        GetPermissionDataAccessInterface getPermissionDataAccessInterface = sqlDataAccessObject;
        CreatePermissionDataAccessInterface createPermissionDataAccessInterface = sqlDataAccessObject;
        UpdatePermissionDataAccessInterface updatePermissionDataAccessInterface = sqlDataAccessObject;
        DeletePermissionDataAccessInterface deletePermissionDataAccessInterface = sqlDataAccessObject;

        CreateProjectSqlDataAccessInterface createProjectSqlDataAccessInterface = sqlDataAccessObject;
        CreateProjectApiDataAccessInterface createProjectApiDataAccessInterface = apiDataAccessObject;
        ShareProjectDataAccessInterface shareProjectDataAccessInterface = sqlDataAccessObject;

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

        LoggedInView loggedInUserView = LoggedInUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInUserViewModel, getPermissionViewModel);
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

        CreateProjectView createProjectView = CreateProjectUseCaseFactory.create(viewManagerModel, createProjectViewModel,
                createProjectApiDataAccessInterface, createProjectSqlDataAccessInterface);
        views.add(createProjectView, createProjectView.viewName);

        ShareProjectView shareProjectView = ShareProjectUseCaseFactory.create(
                viewManagerModel, getProjectViewModel, shareProjectViewModel,
                shareProjectDataAccessInterface, createPermissionDataAccessInterface);
        views.add(shareProjectView, shareProjectView.viewName);

        AddTaskView addTaskView = AddTaskUseCaseFactory.create(viewManagerModel, addTaskViewModel, getTaskViewModel,
                addTaskDataAccessInterface);
        views.add(addTaskView, addTaskView.viewName);

        // set the initial view
        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
