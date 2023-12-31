package domains.user.use_case.signup;

import domains.user.entity.NewUserFactory;
import domains.user.entity.User;

/**
 * @author Henry
 * The signup Interactor class takes inputdata from Controller and returns output data
 */
public class SignupInteractor implements SignupInputBoundary {
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
    }

    @Override
    public void execute(SignupInputData signupInputData) {

        if (signupInputData.getUsername().isEmpty()) {
            userPresenter.prepareFailView("username cannot be blank");
        } else if (signupInputData.getPassword().isEmpty()) {
            userPresenter.prepareFailView("you need a password");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            boolean userExists = false;
            try {
                userExists = userDataAccessObject.existsByName(signupInputData.getUsername());
            } catch (Exception e) {
                userPresenter.prepareFailView(e.getMessage());
                return;
            }

            if (userExists) {
                userPresenter.prepareFailView("User already exists.");
                return;
            }

            User user = NewUserFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            try {
                userDataAccessObject.createUser(user);
            } catch (Exception e) {
                userPresenter.prepareFailView(e.getMessage());
                return;
            }

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}