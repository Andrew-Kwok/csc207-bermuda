package interface_adapter.user.logout;

import use_case.user.upgrade_user.logout.LogoutInputBoundary;
import use_case.user.upgrade_user.logout.LogoutInputData;

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
