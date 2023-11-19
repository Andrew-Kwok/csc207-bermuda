package app;

import app.permission.CreatePermissionUseCaseFactory;
import app.permission.GetPermissionUseCaseFactory;
import app.user.LoginUseCaseFactory;
import app.user.LoggedInUseCaseFactory;
import app.user.SignupUseCaseFactory;
import data_access.cloudsql.SqlConfig;
import data_access.cloudsql.SqlDataAccessObject;
import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.permission.use_case.get_permission.GetPermissionDataAccessInterface;
import interface_adapter.permission.create_permission.CreatePermissionViewModel;
import interface_adapter.permission.get_permission.GetPermissionViewModel;
import interface_adapter.user.loggedin_user.LoggedInViewModel;
import interface_adapter.user.login.LoginViewModel;
import interface_adapter.user.signup.SignupViewModel;
import interface_adapter.view_model.ViewManagerModel;
import domains.user.use_case.login.LoginUserDataAccessInterface;
import domains.user.use_case.signup.SignupUserDataAccessInterface;
import view.permission.CreatePermissionView;
import view.permission.GetPermissionView;
import view.user.LoggedInView;
import view.user.LoginView;
import view.user.SignupView;
import view.ViewManager;

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

        // data access object
        DataSource sqlDataSource = SqlConfig.NewSQL();
        SqlDataAccessObject sqlDataAccessObject = new SqlDataAccessObject(sqlDataSource);

        SignupUserDataAccessInterface signupUserDataAccessInterface = sqlDataAccessObject;
        LoginUserDataAccessInterface loginUserDataAccessInterface = sqlDataAccessObject;
        GetPermissionDataAccessInterface getPermissionDataAccessInterface = sqlDataAccessObject;
        CreatePermissionDataAccessInterface createPermissionDataAccessInterface = sqlDataAccessObject;

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

        GetPermissionView getPermissionView = GetPermissionUseCaseFactory.create(viewManagerModel, createPermissionViewModel, getPermissionViewModel,
                createPermissionDataAccessInterface, getPermissionDataAccessInterface);
        views.add(getPermissionView, getPermissionView.viewName);

        CreatePermissionView createPermissionView = CreatePermissionUseCaseFactory.create(viewManagerModel, createPermissionViewModel, getPermissionViewModel,
                createPermissionDataAccessInterface, getPermissionDataAccessInterface);
        views.add(createPermissionView, createPermissionView.viewName);

        // set the initial view
        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
