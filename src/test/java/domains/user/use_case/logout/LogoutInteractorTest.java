package domains.user.use_case.logout;

import domains.user.use_case.logout.LogoutInputData;
import domains.user.use_case.logout.LogoutInteractor;
import domains.user.use_case.logout.LogoutOutputBoundary;
import domains.user.use_case.logout.LogoutOutputData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogoutInteractorTest {

    @Test
    public void logout() {
        LogoutOutputBoundary logoutOutputBoundary = new LogoutOutputBoundary() {
            @Override
            public void prepareLogoutView(LogoutOutputData logoutOutputData) {
                assertEquals("username", logoutOutputData.getUsername());
            }
        };

        LogoutInteractor logoutInteractor = new LogoutInteractor(logoutOutputBoundary);
        logoutInteractor.execute(new LogoutInputData("username"));
    }
}