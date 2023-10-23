package use_case.signup;

import data_access.ApiDataAccessInterface;
import entity.Account;
import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final ApiDataAccessInterface apiDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            ApiDataAccessInterface apiDataAccessObject,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.apiDataAccessObject = apiDataAccessObject;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if(signupInputData.getUsername().isEmpty()){
            userPresenter.prepareFailView("username cannot be blank");
        }else if(signupInputData.getPassword().isEmpty()){
            userPresenter.prepareFailView("you need a password");
        } else if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            Account newAccount = apiDataAccessObject.createAccount(signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.save(newAccount);
            SignupOutputData signupOutputData = new SignupOutputData(newAccount.getAccountName(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}