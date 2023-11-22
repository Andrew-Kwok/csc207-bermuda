package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

public class LogoutController {
    LogoutInputBoundary logoutInteractor;

    public LogoutController(LogoutInputBoundary logoutInteractor) {
        this.logoutInteractor = logoutInteractor;
    }

    public void execute(String username){
        LogoutInputData logoutInputData = new LogoutInputData(username);
        logoutInteractor.execute(logoutInputData);
    }
}
