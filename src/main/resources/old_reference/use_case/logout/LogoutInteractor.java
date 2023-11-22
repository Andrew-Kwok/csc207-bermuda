package use_case.logout;

public class LogoutInteractor implements LogoutInputBoundary{
    LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutOutputBoundary logoutPresenter) {
        this.logoutPresenter = logoutPresenter;
    }

    @Override
    public void execute(LogoutInputData logoutIutputData) {
        LogoutOutputData logoutOutputData = new LogoutOutputData(logoutIutputData.getUsername());
        logoutPresenter.prepareLogoutView(logoutOutputData);
    }
}
