package app;

import data_access.FileUserDataAccessObject;
import entity.NewUserFactory;
import interface_adapter.loggedin_user.LoggedInUserViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import view.StartView;
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
        LoggedInUserViewModel loggedInViewModel = new LoggedInUserViewModel();

        SignupUserDataAccessInterface signupUserDataAccessInterface;
        LoginUserDataAccessInterface loginUserDataAccessInterface;

        try {
            signupUserDataAccessInterface = new FileUserDataAccessObject("./users.csv", "./projects,csv",new NewUserFactory());
            loginUserDataAccessInterface = (LoginUserDataAccessInterface) signupUserDataAccessInterface;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StartView startView = StartUseCaseFactory.create(viewManagerModel, signupViewModel, loginViewModel, loggedInViewModel,
                signupUserDataAccessInterface, loginUserDataAccessInterface);
        views.add(startView, startView.viewName);

        viewManagerModel.setActiveView(startView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
