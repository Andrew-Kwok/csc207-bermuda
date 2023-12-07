package app;

import app.permission.CreatePermissionUseCaseFactory;
import app.permission.GetPermissionUseCaseFactory;
import app.permission.UpdatePermissionUseCaseFactory;
import app.project.CreateProjectUseCaseFactory;
import app.project.EditProjectUseCaseFactory;
import app.project.GetProjectUseCaseFactory;
import app.project.ShareProjectPageUseCaseFactory;
import app.task.AddTaskUseCaseFactory;
import app.task.EditTaskUseCaseFactory;
import app.task.GetTaskUseCaseFactory;
import app.user.LoggedInUseCaseFactory;
import app.user.LoginUseCaseFactory;
import app.user.SignupUseCaseFactory;
import config.Config;
import constant.ViewConstant;
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
import domains.project.use_case.edit_project.EditProjectDataAccessInterface;
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
import interface_adapter.project.delete_project.DeleteProjectViewModel;
import interface_adapter.project.edit_project.EditProjectViewModel;
import interface_adapter.project.get_project.GetProjectViewModel;
import interface_adapter.project.share_project.ShareProjectViewModel;
import interface_adapter.project.share_project_page.ShareProjectPageViewModel;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.task.close_task.CloseTaskViewModel;
import interface_adapter.task.edit_task.EditTaskViewModel;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.user.loggedin.LoggedInViewModel;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.user.signup.SignupViewModel;
import interface_adapter.view_model.ViewManagerModel;
import org.junit.jupiter.api.Test;
import view.ViewManager;
import view.permission.CreatePermissionView;
import view.permission.GetPermissionView;
import view.permission.UpdatePermissionView;
import view.project.CreateProjectView;
import view.project.EditProjectView;
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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BermudaTest {

    @Test
    void main() {
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
        EditProjectViewModel editProjectViewModel = new EditProjectViewModel();
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
        EditProjectDataAccessInterface editProjectDataAccessInterface = apiDataAccessObject;
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
                editProjectViewModel,
                getTaskViewModel,
                createProjectApiDataAccessInterface,
                createProjectSqlDataAccessInterface,
                getProjectApiDataAccessInterface,
                getProjectSqlDataAccessInterface,
                deleteProjectApiDataAccessInterface,
                deleteProjectSqlDataAccessInterface,
                shareProjectPageDataAccessInterface);
        views.add(getProjectView, getProjectView.viewName);

        EditProjectView editProjectView = EditProjectUseCaseFactory.create(
                viewManagerModel,
                editProjectViewModel,
                getProjectViewModel,
                editProjectDataAccessInterface,
                getProjectApiDataAccessInterface,
                getProjectSqlDataAccessInterface);
        views.add(editProjectView, editProjectView.viewName);

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

        // begin testing
        // start application
        // initial
        boolean testInitial = true;
        String testUser = "test_user3";
        String testUser2 = "test_user4";
        String testPassword = "test_password";
        String testPassword2 = "test_password2";

        if (testInitial) {
            assertEquals(viewManagerModel.getActiveView(), loginView.viewName);
            assertTrue(loginViewModel.getState().getUsername().isEmpty());
            assertTrue(loginViewModel.getState().getPassword().isEmpty());
            assertNull(loginViewModel.getState().getUsernameError());
            assertNull(loginViewModel.getState().getPasswordError());
            assertTrue(loginView.getUsernameInputField().getText().isEmpty());
            assertTrue(loginView.getPasswordInputField().getText().isEmpty());

            // signup
            loginView.getSignUpButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), signupView.viewName);
            //initial
            assertTrue(signupViewModel.getState().getUsername().isEmpty());
            assertTrue(signupViewModel.getState().getPassword().isEmpty());
            assertTrue(signupViewModel.getState().getRepeatPassword().isEmpty());
            assertNull(signupViewModel.getState().getUsernameError());
            assertNull(signupViewModel.getState().getPasswordError());
            assertNull(signupViewModel.getState().getRepeatPasswordError());
            assertTrue(signupView.getUsernameInputField().getText().isEmpty());
            assertTrue(signupView.getPasswordInputField().getText().isEmpty());
            assertTrue(signupView.getRepeatPasswordInputField().getText().isEmpty());

//        // signup
//        signupView.getUsernameInputField().setText("test_user");
//        signupView.getPasswordInputField().setText("test_password");
//
//        // test wrong password
//        signupView.getRepeatPasswordInputField().setText("different");
//        signupView.getSignUpButton().doClick();
//        assertEquals(viewManagerModel.getActiveView(), signupView.viewName);
//
//        // make sure password is cleared
//        assertTrue(signupView.getPasswordInputField().getText().isEmpty());
//        assertTrue(signupView.getRepeatPasswordInputField().getText().isEmpty());
//
            // signup
            signupView.getUsernameInputField().setText(testUser);
            signupView.getPasswordInputField().setText(testPassword);
            signupView.getRepeatPasswordInputField().setText(testPassword);
            signupView.getSignUpButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), loginView.viewName);

//        // assert usernames are unique
//        loginView.getSignUpButton().doClick();
//        signupView.getUsernameInputField().setText("test_user");
//        signupView.getPasswordInputField().setText("test_password");
//        signupView.getRepeatPasswordInputField().setText("test_password");
//        signupView.getSignUpButton().doClick();
//        assertEquals(viewManagerModel.getActiveView(), signupView.viewName);
//        assertNotNull(signupViewModel.getState().getUsernameError());
//
            // create other user
            loginView.getSignUpButton().doClick();


            signupView.getUsernameInputField().setText(testUser2);
            signupView.getPasswordInputField().setText(testPassword2);
            signupView.getRepeatPasswordInputField().setText(testPassword2);
            signupView.getSignUpButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), loginView.viewName);

            // back to login page
            assertEquals(loginView.getUsernameInputField().getText(), testUser2);
        }

        loginView.getUsernameInputField().setText(testUser2);
        loginView.getPasswordInputField().setText(testPassword2);
        loginView.getLoginButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), loggedInUserView.viewName);
        assertEquals(loggedInUserViewModel.getState().getUser().getUsername(), testUser2);
        assertEquals(loggedInUserViewModel.getState().getUser().getPassword(), testPassword2);
        String otherUserID = loggedInUserViewModel.getState().getUser().getUserID();
        assertNotNull(otherUserID);
        assertNull(loginViewModel.getState().getUsernameError());

        // logout
        loggedInUserView.getLogOutButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), loginView.viewName);
        assertNull(loginViewModel.getState().getUsernameError());
        assertNull(loginViewModel.getState().getPasswordError());
        assertEquals(loginView.getUsernameInputField().getText(), testUser2);
        assertTrue(loginView.getPasswordInputField().getText().isEmpty());

        // log back in
        loginView.getUsernameInputField().setText(testUser);
        loginView.getPasswordInputField().setText(testPassword);
        loginView.getLoginButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), loggedInUserView.viewName);
        assertEquals(loggedInUserViewModel.getState().getUser().getUsername(), testUser);
        assertEquals(loggedInUserViewModel.getState().getUser().getPassword(), testPassword);
        String userID = loggedInUserViewModel.getState().getUser().getUserID();

        // check project
        loggedInUserView.getCheckProjectButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), getProjectView.viewName);
        assertNull(getProjectViewModel.getState().getGetProjectError());

        // initially no project
        boolean projectNotCreated = true;
        if (projectNotCreated) {
            assertTrue(getProjectViewModel.getState().getProjects().isEmpty());
            assertEquals(getProjectViewModel.getState().getUserId(), userID);

            // create project
            getProjectView.getCreateProjectButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), createProjectView.viewName);
            assertTrue(createProjectViewModel.getState().getProjectName().isEmpty());
            assertNull(createProjectViewModel.getState().getProjectError());
            assertTrue(createProjectViewModel.getState().getProjectName().isEmpty());
            assertTrue(createProjectView.getProjectNameInputField().getText().isEmpty());
            createProjectView.getProjectNameInputField().setText("test_project");
            createProjectView.getCreateProjectButton().doClick();
        }

        // check project
        assertEquals(viewManagerModel.getActiveView(), getProjectView.viewName);
        assertNull(getProjectViewModel.getState().getGetProjectError());
        assertNotNull(getProjectViewModel.getState().getProjects());
        assertEquals(1, getProjectViewModel.getState().getProjects().size());
        assertEquals("test_project", getProjectViewModel.getState()
                .getProjects().get(0)
                .get(ViewConstant.PROJECT_NAME));

        Map<String, String> project = getProjectViewModel.getState()
                .getProjects().get(0);
        String projectID = project.get(ViewConstant.PROJECT_ID);

        // go back
        getProjectView.getGoBackButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), loggedInUserView.viewName);

        // check permission
        boolean testPermission = true;
        if (testPermission) {
            loggedInUserView.getCheckPermissionButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), getPermissionView.viewName);
            assertNull(getPermissionViewModel.getState().getGetPermissionError());
//        assertTrue(getPermissionViewModel.getState().getPermissions()
//                .stream()
//                .anyMatch((Map<String, String> permission) ->
//                        permission.get(ViewConstant.PROJECT_ID).equals(projectID) &&
//                        permission.get(ViewConstant.USER_ID).equals(userID)));

//            Map<String, String> projectPermission = getPermissionViewModel.getState().getPermissions()
//                    .stream()
//                    .filter((Map<String, String> permission) ->
//                            permission.get(ViewConstant.PROJECT_ID).equals(projectID) &&
//                                    permission.get(ViewConstant.USER_ID).equals(userID))
//                    .findFirst()
//                    .get();
//
            // create permission
            getPermissionView.getCreatePermissionButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), createPermissionView.viewName);
            assertNull(createPermissionViewModel.getState().getCreatePermissionError());
            assertTrue(createPermissionView.getUserIdInputField().getText().isEmpty());
            assertTrue(createPermissionView.getProjectIdInputField().getText().isEmpty());
            assertTrue(createPermissionView.getPermissionNameInputField().getText().isEmpty());
            assertTrue(createPermissionView.getPermissionDescriptionInputField().getText().isEmpty());

            // go back
            createPermissionView.getCancelButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), getPermissionView.viewName);

            // create permission
            getPermissionView.getCreatePermissionButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), createPermissionView.viewName);
            createPermissionView.getUserIdInputField().setText(userID);
            createPermissionView.getProjectIdInputField().setText(projectID);
            createPermissionView.getPermissionNameInputField().setText("test_permission");
            createPermissionView.getPermissionDescriptionInputField().setText("test_permission_description");
            createPermissionView.getCreatePermissionButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), getPermissionView.viewName);
            assertNull(getPermissionViewModel.getState().getGetPermissionError());
            assertTrue(getPermissionViewModel.getState().getPermissions()
                    .stream()
                    .anyMatch((Map<String, String> permission) ->
                            permission.get(ViewConstant.PROJECT_ID).equals(projectID) &&
                                    permission.get(ViewConstant.USER_ID).equals(userID) &&
                                    permission.get(ViewConstant.PERMISSION_NAME).equals("test_permission") &&
                                    permission.get(ViewConstant.PERMISSION_DESCRIPTION).equals("test_permission_description")));

            // keep new permission
            Map<String, String> newPermission = getPermissionViewModel.getState().getPermissions()
                    .stream()
                    .filter((Map<String, String> permission) ->
                            permission.get(ViewConstant.PROJECT_ID).equals(projectID) &&
                                    permission.get(ViewConstant.USER_ID).equals(userID) &&
                                    permission.get(ViewConstant.PERMISSION_NAME).equals("test_permission") &&
                                    permission.get(ViewConstant.PERMISSION_DESCRIPTION).equals("test_permission_description"))
                    .findFirst()
                    .get();

            // update permission
            //if no permission chosen
            getPermissionView.getUpdatePermissionButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), getPermissionView.viewName);

            // choose permission
            getPermissionView.getPermissionList().setSelectedValue(newPermission, true);
            getPermissionView.getUpdatePermissionButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), updatePermissionView.viewName);
            assertEquals(updatePermissionViewModel.getState().getPermissionId(),
                    newPermission.get(ViewConstant.PERMISSION_ID));
            assertEquals(updatePermissionViewModel.getState().getUserId(),
                    newPermission.get(ViewConstant.USER_ID));
            assertEquals(updatePermissionViewModel.getState().getProjectId(),
                    newPermission.get(ViewConstant.PROJECT_ID));
            assertEquals(updatePermissionViewModel.getState().getPermissionName(),
                    newPermission.get(ViewConstant.PERMISSION_NAME));
            assertEquals(updatePermissionViewModel.getState().getPermissionDescription(),
                    newPermission.get(ViewConstant.PERMISSION_DESCRIPTION));

            // go back
            updatePermissionView.getCancelButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), getPermissionView.viewName);

            // update
            getPermissionView.getPermissionList().setSelectedValue(newPermission, true);
            getPermissionView.getUpdatePermissionButton().doClick();
            updatePermissionView.getPermissionNameInputField().setText("test_permission_updated");
            updatePermissionView.getPermissionDescriptionInputField().setText("test_permission_description_updated");
            updatePermissionView.getUpdatePermissionButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), getPermissionView.viewName);
            assertNull(getPermissionViewModel.getState().getGetPermissionError());

            // check updated permission
            Map<String, String> updatedPermission = getPermissionViewModel.getState().getPermissions()
                    .stream()
                    .filter((Map<String, String> permission) ->
                            permission.get(ViewConstant.PROJECT_ID).equals(projectID) &&
                                    permission.get(ViewConstant.USER_ID).equals(userID) &&
                                    permission.get(ViewConstant.PERMISSION_NAME).equals("test_permission_updated") &&
                                    permission.get(ViewConstant.PERMISSION_DESCRIPTION).equals("test_permission_description_updated"))
                    .findFirst()
                    .get();

            assertEquals(updatedPermission.get(ViewConstant.PERMISSION_ID),
                    newPermission.get(ViewConstant.PERMISSION_ID));
            assertEquals(updatedPermission.get(ViewConstant.PERMISSION_NAME), "test_permission_updated");
            assertEquals(updatedPermission.get(ViewConstant.PERMISSION_DESCRIPTION), "test_permission_description_updated");

            // delete permission
            // if no permission chosen
            getPermissionView.getDeletePermissionButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), getPermissionView.viewName);

            // choose permission
            getPermissionView.getPermissionList().setSelectedValue(updatedPermission, true);
            getPermissionView.getDeletePermissionButton().doClick();

            // confirm delete
            assertEquals(viewManagerModel.getActiveView(), getPermissionView.viewName);
            assertNull(getPermissionViewModel.getState().getGetPermissionError());
            assertFalse(getPermissionViewModel.getState().getPermissions()
                    .stream()
                    .anyMatch((Map<String, String> permission) ->
                            permission.get(ViewConstant.PERMISSION_ID)
                                    .equals(updatedPermission.get(ViewConstant.PERMISSION_ID))
                    ));
        }

        // check project
        getPermissionView.getGoBackButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), loggedInUserView.viewName);
        loggedInUserView.getCheckProjectButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), getProjectView.viewName);
        assertNull(getProjectViewModel.getState().getGetProjectError());
        assertNotNull(getProjectViewModel.getState().getProjects());

        // edit project
        getProjectView.getProjectList().setSelectedValue(project, true);
        getProjectView.getEditProjectButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), editProjectView.viewName);
        assertEquals(editProjectViewModel.getState().getProjectName(), project.get(ViewConstant.PROJECT_NAME));
        editProjectView.getProjectNameInputField().setText("test_project_updated");
        editProjectView.getEditProjectButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), getProjectView.viewName);
        assertNull(getProjectViewModel.getState().getGetProjectError());
        assertNotNull(getProjectViewModel.getState().getProjects());
        Map<String, String> prevProject = project;
        project = getProjectViewModel.getState().getProjects()
                .stream()
                .filter((Map<String, String> project_) ->
                        project_.get(ViewConstant.PROJECT_ID).equals(prevProject.get(ViewConstant.PROJECT_ID)))
                .findFirst()
                .get();
        assertEquals(project.get(ViewConstant.PROJECT_NAME), "test_project_updated");

        // share project
        boolean shared = true;
        if (!shared) {
            getProjectView.getProjectList().setSelectedValue(project, true);
            getProjectView.getShareProjectButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), shareProjectPageView.viewName);
            assertEquals(shareProjectPageViewModel.getState().getProjectId(), project.get(ViewConstant.PROJECT_ID));
            assertEquals(shareProjectPageViewModel.getState().getProjectName(), project.get(ViewConstant.PROJECT_NAME));

            // no user chosen
            shareProjectPageView.getShareProjectButton().doClick();
            assertEquals(viewManagerModel.getActiveView(), shareProjectPageView.viewName);

            // select other user
            for (int i = 0; i < shareProjectPageView.getUserList().getModel().getSize(); i++) {
                if (shareProjectPageView.getUserList().getModel()
                        .getElementAt(i)
                        .getUserId()
                        .equals(otherUserID)) {
                    shareProjectPageView.getUserList().setSelectedIndex(i);
                    break;
                }
            }
            shareProjectPageView.getShareProjectButton().doClick();

            // manually choose yes option
            assertEquals(viewManagerModel.getActiveView(), getProjectView.viewName);
            assertNull(getProjectViewModel.getState().getGetProjectError());
        }
        // check tasks
        getProjectView.getProjectList().setSelectedValue(project, true);
        getProjectView.getCheckTaskButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), getTaskView.viewName);
        assertNull(getTaskViewModel.getState().getGetTaskError());

        // add task
        getTaskView.getAddTaskButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), addTaskView.viewName);
        assertTrue(addTaskViewModel.getState().getTaskName().isEmpty());
        assertTrue(addTaskViewModel.getState().getTaskDescription().isEmpty());
        assertNull(addTaskViewModel.getState().getAddTaskError());
        assertTrue(addTaskView.getTaskNameInputField().getText().isEmpty());
        assertTrue(addTaskView.getTaskDescriptionInputField().getText().isEmpty());
        addTaskView.getTaskNameInputField().setText("test_task");
        addTaskView.getTaskDescriptionInputField().setText("test_task_description");
        addTaskView.getAddTaskButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), getTaskView.viewName);
        assertNull(getTaskViewModel.getState().getGetTaskError());
        assertNotNull(getTaskViewModel.getState().getTasks());
        assertEquals(1, getTaskViewModel.getState().getTasks().size());
        Map<String, String> task = getTaskViewModel.getState().getTasks().get(0);
        assertEquals(task.get(ViewConstant.TASK_NAME), "test_task");
        assertEquals(task.get(ViewConstant.TASK_DESCRIPTION), "test_task_description");

        // edit task
        getTaskView.getTaskList().setSelectedValue(task, true);
        getTaskView.getEditTaskButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), editTaskView.viewName);
        assertEquals(editTaskViewModel.getState().getTaskName(), task.get(ViewConstant.TASK_NAME));
        assertEquals(editTaskViewModel.getState().getTaskDescription(), task.get(ViewConstant.TASK_DESCRIPTION));
        editTaskView.getTaskNameInputField().setText("test_task_updated");
        editTaskView.getTaskDescriptionInputField().setText("test_task_description_updated");
        editTaskView.getEditTaskButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), getTaskView.viewName);
        assertNull(getTaskViewModel.getState().getGetTaskError());
        assertNotNull(getTaskViewModel.getState().getTasks());
        Map<String, String> prevTask = task;
        task = getTaskViewModel.getState().getTasks()
                .stream()
                .filter((Map<String, String> task_) ->
                        task_.get(ViewConstant.TASK_ID).equals(prevTask.get(ViewConstant.TASK_ID)))
                .findFirst()
                .get();
        assertEquals(task.get(ViewConstant.TASK_NAME), "test_task_updated");
        assertEquals(task.get(ViewConstant.TASK_DESCRIPTION), "test_task_description_updated");

        // close task
        // yes option
        getTaskView.getTaskList().setSelectedValue(task, true);
        getTaskView.getCloseTaskButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), getTaskView.viewName);
        assertNull(getTaskViewModel.getState().getGetTaskError());
        assertTrue(getTaskViewModel.getState().getTasks().isEmpty());
        assertEquals(viewManagerModel.getActiveView(), getTaskView.viewName);

        // go back
        getTaskView.getGoBackButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), getProjectView.viewName);

        // delete project
        getProjectView.getProjectList().setSelectedValue(project, true);
        getProjectView.getDeleteProjectButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), getProjectView.viewName);

        // logout and exit
        loggedInUserView.getLogOutButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), loginView.viewName);
        loginView.getExitButton().doClick();
        assertEquals(viewManagerModel.getActiveView(), loginView.viewName);

    }
}