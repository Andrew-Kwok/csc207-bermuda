package use_case.user.upgrade_user.login;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(LoginOutputData error);
}