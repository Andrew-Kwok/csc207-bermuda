package domains.project.use_case.delete_project;

import data_access.in_memory.InMemoryAPIDataAccessObject;
import data_access.in_memory.InMemorySQLDataAccessObject;
import domains.project.entity.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class DeleteProjectInteractorTest {
    public static DeleteProjectApiDataAccessInterface apidao;
    public static DeleteProjectSqlDataAccessInterface sqldao;

    @BeforeAll
    public static void initiateDAO() {
        InMemoryAPIDataAccessObject imapidao = new InMemoryAPIDataAccessObject();
        InMemorySQLDataAccessObject imsqldao = new InMemorySQLDataAccessObject();
        for (int i = 1; i < 5; i++) {
            try {
                imapidao.createProject("proj" + String.valueOf(i));
                imsqldao.permissions.add(List.of(String.valueOf(i), String.valueOf(i), String.valueOf(i), "owner", ""));
                imsqldao.users.add(List.of(String.valueOf(i), "test" + String.valueOf(i), "pass" + String.valueOf(i), "0"));
            } catch (Exception e) {
                System.out.println("error in initiation, how this happen?");
            }
        }
        imsqldao.permissions.add(List.of("6", "2", "1", "editor", ""));
        apidao = imapidao;
        sqldao = imsqldao;
    }

    @Test
    public void successTest() {
        final String PROJECT_ID = "1";
        final String PROJECT_NAME = "proj1";

        final DeleteProjectOutputBoundary presenter = new DeleteProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteProjectOutputData output) {
                assertEquals(output.getProjectId(), PROJECT_ID);
                try {
                assertFalse(
                        ((InMemoryAPIDataAccessObject) apidao).getProjects().stream()
                            .anyMatch(
                                (Project e) -> e.getProjectID().equals(PROJECT_ID) &&
                                        e.getProjectName().equals(PROJECT_NAME)
                            ));
                } catch (Exception e) {
                    fail("exception in successTest ApiDAO getProjects(), how this happen?");
                }
            }

            @Override
            public void prepareFailView(String errMessage) {
                fail("fail view not expected");
            }
        };

        final DeleteProjectInputData input = new DeleteProjectInputData(PROJECT_ID);
        final DeleteProjectInteractor interactor = new DeleteProjectInteractor(presenter,apidao, sqldao);
        interactor.execute(input);
    }

    @Test
    public void DAOExceptionTest() {
        final String PROJECT_ID = "test dao failure";
        final String PROJECT_NAME = "proj1";
        final DeleteProjectOutputBoundary presenter = new DeleteProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteProjectOutputData output) {
                fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        final DeleteProjectInputData input = new DeleteProjectInputData(PROJECT_ID);
        final DeleteProjectInteractor interactor = new DeleteProjectInteractor(presenter, apidao, sqldao);
        interactor.execute(input);
    }
}