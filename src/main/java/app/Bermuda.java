package app;

import data_access.FileUserDataAccessObject;
import entity.NewUserFactory;
import interface_adapter.loggedin_user.LoggedInUserViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import view.LoggedInUserView;
import view.LoginView;
import view.SignupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

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
        LoggedInUserViewModel loggedInUserViewModel = new LoggedInUserViewModel();

        SignupUserDataAccessInterface signupUserDataAccessInterface;
        LoginUserDataAccessInterface loginUserDataAccessInterface;

        try {
            signupUserDataAccessInterface = new FileUserDataAccessObject("./users.csv", "./projects,csv",new NewUserFactory());
            loginUserDataAccessInterface = (LoginUserDataAccessInterface) signupUserDataAccessInterface;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, loginViewModel, loggedInUserViewModel,
                signupUserDataAccessInterface, loginUserDataAccessInterface);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInUserViewModel, loginUserDataAccessInterface);
        views.add(loginView, loginView.viewName);

        LoggedInUserView loggedInUserView = LogoutUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInUserViewModel);
        views.add(loggedInUserView, loggedInUserView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
