package domains.project.use_case.share_project_page;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import domains.project.use_case.share_project.ShareProjectDataAccessInterface;
import domains.project.use_case.share_project_page.*;
import interface_adapter.project.share_project_page.*;
import data_access.in_memory.*;

import java.util.List;

class ShareProjectPageInteractorTest {
    public static ShareProjectPageDataAccessInterface dao;
    @BeforeAll
    public static void initiateSQLDAO() {
        InMemorySQLDataAccessObject imsqldao = new InMemorySQLDataAccessObject();
        imsqldao.permissions.add(List.of("1", "1", "1", "owner", ""));
        imsqldao.permissions.add(List.of("2", "2", "2", "owner", ""));
        imsqldao.permissions.add(List.of("3", "3", "3", "owner", ""));
        imsqldao.permissions.add(List.of("4", "4", "4", "owner", ""));
        imsqldao.permissions.add(List.of("5", "5", "5", "owner", ""));
        imsqldao.permissions.add(List.of("6", "2", "1", "editor", ""));
        imsqldao.users.add(List.of("1", "test1", "pass1", "0"));
        imsqldao.users.add(List.of("2", "test2", "pass2", "0"));
        imsqldao.users.add(List.of("3", "test3", "pass3", "0"));
        imsqldao.users.add(List.of("4", "test4", "pass4", "0"));
        imsqldao.users.add(List.of("5", "test5", "pass5", "0"));
        dao = imsqldao;
    }
    @Test
    public void successTest() {
        final String PROJECT_ID = "1";
        final String PROJECT_NAME = "TEST";

        final ShareProjectPageOutputBoundary presenter = new ShareProjectPageOutputBoundary() {
            @Override
            public void prepareSuccessView(ShareProjectPageOutputData output) {
                assertNull(output.getErrorCode());
                assertEquals(output.getProjectId(), PROJECT_ID);
                assertEquals(output.getProjectName(), PROJECT_NAME);
                assertEquals(output.getUsersNameAndId(), List.of(
                        List.of("test3", "3"),
                        List.of("test4", "4"),
                        List.of("test5", "5")
                ));
            }

            @Override
            public void prepareFailView(ShareProjectPageOutputData output) {
                fail("fail view not expected");
            }
        };

        final ShareProjectPageInputData input = new ShareProjectPageInputData("1", PROJECT_ID, PROJECT_NAME);
        final ShareProjectPageInteractor interactor = new ShareProjectPageInteractor(presenter, dao);
        interactor.execute(input);
    }

    @Test
    public void DAOExceptionTest() {
        final String PROJECT_ID = "test dao failure";
        final String PROJECT_NAME = null;

        final ShareProjectPageOutputBoundary presenter = new ShareProjectPageOutputBoundary() {
            @Override
            public void prepareSuccessView(ShareProjectPageOutputData output) {
                fail("success view not expected");
            }

            @Override
            public void prepareFailView(ShareProjectPageOutputData output) {
                assertNotNull(output.getErrorCode());
                assertNull(output.getProjectId());
                assertNull(output.getProjectName());
                assertNull(output.getUsersNameAndId());
            }
        };

        final ShareProjectPageInputData input = new ShareProjectPageInputData("1", PROJECT_ID, PROJECT_NAME);
        final ShareProjectPageInteractor interactor = new ShareProjectPageInteractor(presenter, dao);
        interactor.execute(input);
    }
}