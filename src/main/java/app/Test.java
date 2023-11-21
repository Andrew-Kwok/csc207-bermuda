//package app;
//
//// import data_access.todoist.ApiDataAccessInterface;
//import app.permission.CreatePermissionUseCaseFactory;
//import app.permission.GetPermissionUseCaseFactory;
//import app.task.AddTaskUseCaseFactory;
//import app.user.LoggedInUseCaseFactory;
//import app.user.LoginUseCaseFactory;
//import app.user.SignupUseCaseFactory;
//import data_access.cloudsql.SqlConfig;
//import data_access.cloudsql.SqlDataAccessObject;
//import data_access.todoist.ApiDataAccessObject;
//import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
//import domains.permission.use_case.get_permission.GetPermissionDataAccessInterface;
//import domains.project.entity.Project;
//import domains.task.entity.Task;
//import domains.task.use_case.add_task.AddTaskDataAccessInterface;
//import domains.task.use_case.add_task.AddTaskInputData;
//import domains.task.use_case.add_task.AddTaskInteractor;
//import domains.task.use_case.add_task.AddTaskOutputBoundary;
//import domains.user.use_case.login.LoginUserDataAccessInterface;
//import domains.user.use_case.signup.SignupUserDataAccessInterface;
//import interface_adapter.permission.create_permission.CreatePermissionViewModel;
//import interface_adapter.permission.get_permission.GetPermissionViewModel;
//import interface_adapter.task.add_task.AddTaskPresenter;
//import interface_adapter.task.add_task.AddTaskViewModel;
//import interface_adapter.task.get_task.GetTaskViewModel;
//import interface_adapter.user.loggedin_user.LoggedInViewModel;
//import interface_adapter.user.login.LoginViewModel;
//import interface_adapter.user.signup.SignupViewModel;
//import interface_adapter.view_model.ViewManagerModel;
//import view.ViewManager;
//import view.permission.CreatePermissionView;
//import view.permission.GetPermissionView;
//import view.task.AddTaskView;
//import view.user.LoggedInView;
//import view.user.LoginView;
//import view.user.SignupView;
//
//import javax.sql.DataSource;
//import javax.swing.*;
//import java.awt.*;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.Month;
//
//public class Test {
//    public static void main(String[] args) {
////        ApiDataAccessInterface api = new ApiDataAccessObject();
////        Project project = api.getProject("2320965999");
////
////        System.out.println(project);
//
//        /*AddTaskDataAccessInterface task_api = new ApiDataAccessObject();
//        Task task = new Task("0000000000", "2320965999", "test", "content-1");
//
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        AddTaskViewModel addtaskViewModel = new AddTaskViewModel();
//        AddTaskDataAccessInterface addTaskDAO = new ApiDataAccessObject();
//        AddTaskOutputBoundary addTaskOutBound = new AddTaskPresenter(viewManagerModel, addtaskViewModel, addTaskDAO);
//        AddTaskInteractor addTaskInteractor = new AddTaskInteractor(addTaskDAO, addTaskOutBound);
//
//        LocalDateTime dateTime = LocalDate.of(2024, Month.JANUARY, 18).atStartOfDay();
//        AddTaskInputData addTaskInputData = new AddTaskInputData("task-1", "test-1", dateTime, "2320965999");
//        addTaskInteractor.execute(addTaskInputData);*/
//
//        JFrame application = new JFrame("Bermuda Example");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        CardLayout cardLayout = new CardLayout();
//
//        // The various View objects. Only one view is visible at a time.
//        JPanel views = new JPanel(cardLayout);
//        application.add(views);
//
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);
//
//        // build the view manager and add views
//        SignupViewModel signupViewModel = new SignupViewModel();
//        LoginViewModel loginViewModel = new LoginViewModel();
//        LoggedInViewModel loggedInUserViewModel = new LoggedInViewModel();
//        GetPermissionViewModel getPermissionViewModel = new GetPermissionViewModel();
//        CreatePermissionViewModel createPermissionViewModel = new CreatePermissionViewModel();
//        AddTaskViewModel addTaskViewModel = new AddTaskViewModel();
//
//        // data access object
//        DataSource sqlDataSource = SqlConfig.NewSQL();
//        SqlDataAccessObject sqlDataAccessObject = new SqlDataAccessObject(sqlDataSource);
//        ApiDataAccessObject apiDataAccessObject = new ApiDataAccessObject();
//
//        SignupUserDataAccessInterface signupUserDataAccessInterface = sqlDataAccessObject;
//        LoginUserDataAccessInterface loginUserDataAccessInterface = sqlDataAccessObject;
//        GetPermissionDataAccessInterface getPermissionDataAccessInterface = sqlDataAccessObject;
//        CreatePermissionDataAccessInterface createPermissionDataAccessInterface = sqlDataAccessObject;
//        AddTaskDataAccessInterface addTaskDataAccessInterface = apiDataAccessObject;
//
////        try {
////            signupUserDataAccessInterface = new FileUserDataAccessObject("./users.csv", "./projects,csv");
////            loginUserDataAccessInterface = (LoginUserDataAccessInterface) signupUserDataAccessInterface;
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
//
//        // create views
//        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, loginViewModel, loggedInUserViewModel,
//                signupUserDataAccessInterface, loginUserDataAccessInterface);
//        views.add(signupView, signupView.viewName);
//
//        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInUserViewModel,
//                loginUserDataAccessInterface);
//        views.add(loginView, loginView.viewName);
//
//        LoggedInView loggedInUserView = LoggedInUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInUserViewModel, getPermissionViewModel);
//        views.add(loggedInUserView, loggedInUserView.viewName);
//
//        GetPermissionView getPermissionView = GetPermissionUseCaseFactory.create(viewManagerModel, createPermissionViewModel, getPermissionViewModel,
//                createPermissionDataAccessInterface, getPermissionDataAccessInterface);
//        views.add(getPermissionView, getPermissionView.viewName);
//
//        CreatePermissionView createPermissionView = CreatePermissionUseCaseFactory.create(viewManagerModel, createPermissionViewModel, getPermissionViewModel,
//                createPermissionDataAccessInterface, getPermissionDataAccessInterface);
//        views.add(createPermissionView, createPermissionView.viewName);
//
//        AddTaskView addTaskView = AddTaskUseCaseFactory.create(viewManagerModel, addTaskViewModel, addTaskDataAccessInterface);
//        views.add(addTaskView, addTaskView.viewName);
//
//        // set the initial view
//        viewManagerModel.setActiveView(addTaskView.viewName);
//        viewManagerModel.firePropertyChanged();
//
//        System.out.println("view " + addTaskView.viewName);
//
//        application.pack();
//        application.setVisible(true);
//
//    }
//}
