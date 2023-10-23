package app;

import data_access.ApiDataAccessInterface;
import entity.NewUserFactory;
import entity.UserFactory;
import interface_adapter.loggedin_user.LoggedInUserViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.view_model.ViewManagerModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {
    private SignupUseCaseFactory() {
    }

    public static SignupView create(ViewManagerModel viewManagerModel,
                                    SignupViewModel signupViewModel, LoginViewModel loginViewModel, LoggedInUserViewModel loggedInUserViewModel,
                                    SignupUserDataAccessInterface signupUserDataAccessInterface, LoginUserDataAccessInterface loginUserDataAccessInterface,
                                    ApiDataAccessInterface apiDataAccessInterface) {
        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel,
                    signupUserDataAccessInterface, apiDataAccessInterface);
            LoginController loginController = createUserLoginUseCase(viewManagerModel, loggedInUserViewModel, loginViewModel, loginUserDataAccessInterface, apiDataAccessInterface);
            return new SignupView(signupController, signupViewModel, loginController, loginViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel,
                                                            SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                                                            SignupUserDataAccessInterface userDataAccessObject,
                                                            ApiDataAccessInterface apiDataAccessInterface) throws IOException {
        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new NewUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject,apiDataAccessInterface, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel,
                                                          LoggedInUserViewModel loggedInUserViewModel, LoginViewModel loginViewModel,
                                                          LoginUserDataAccessInterface loginUserDataAccessInterface,
                                                          ApiDataAccessInterface apiDataAccessInterface) throws IOException {

        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInUserViewModel,loginViewModel);

        UserFactory userFactory = new NewUserFactory();

        LoginInputBoundary loginInputBoundary = new LoginInteractor(loginUserDataAccessInterface, apiDataAccessInterface,loginOutputBoundary);

        return new LoginController(loginInputBoundary);
    }

}
