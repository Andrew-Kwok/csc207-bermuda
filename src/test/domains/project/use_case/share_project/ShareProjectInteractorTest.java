package domains.project.use_case.share_project;

import data_access.in_memory.InMemorySQLDataAccessObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShareProjectInteractorTest {
    public static ShareProjectDataAccessInterface dao;
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
        final String OTHER_USER_ID = "2";
        final String OTHER_USER_NAME = "test2";

        final ShareProjectOutputBoundary presenter = new ShareProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(ShareProjectOutputData output) {
                assertEquals(output.getProjectId(), PROJECT_ID);
                assertEquals(output.getOtherUserId(), OTHER_USER_ID);
                assertEquals(output.getOtherUserName(), OTHER_USER_NAME);
            }

            @Override
            public void prepareFailView(Integer errCode) {
                fail("fail view not expected");
            }
        };

        final ShareProjectInputData input = new ShareProjectInputData(PROJECT_ID, OTHER_USER_ID, OTHER_USER_NAME);
        final ShareProjectInteractor interactor = new ShareProjectInteractor(dao, presenter);
        interactor.execute(input);
    }

    @Test
    public void DAOExceptionTest() {
        final String PROJECT_ID = "test dao failure";
        final String OTHER_USER_ID = "1";
        final String OTHER_USER_NAME = "test1";
        final ShareProjectOutputBoundary presenter = new ShareProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(ShareProjectOutputData output) {
                fail("success view not expected");
            }

            @Override
            public void prepareFailView(Integer errCode) {
                assertNotNull(errCode);
            }
        };

        final ShareProjectInputData input = new ShareProjectInputData(PROJECT_ID, OTHER_USER_ID, OTHER_USER_NAME);
        final ShareProjectInteractor interactor = new ShareProjectInteractor(dao, presenter);
        interactor.execute(input);
    }
}