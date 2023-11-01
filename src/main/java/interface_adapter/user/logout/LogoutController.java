package interface_adapter.user.logout;

import domains.user.use_case.logout.LogoutInputBoundary;
import domains.user.use_case.logout.LogoutInputData;

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
