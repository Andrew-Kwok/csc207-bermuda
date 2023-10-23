package use_case.login;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData userInfo);

    void prepareFailView(int errorCode, String message);
}