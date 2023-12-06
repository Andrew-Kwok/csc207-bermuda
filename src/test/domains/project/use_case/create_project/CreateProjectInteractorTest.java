package domains.project.use_case.create_project;

import data_access.in_memory.InMemoryAPIDataAccessObject;
import data_access.in_memory.InMemorySQLDataAccessObject;
import domains.project.entity.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateProjectInteractorTest {
    private static CreateProjectApiDataAccessInterface apidao;
    private static CreateProjectSqlDataAccessInterface sqldao;

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
        final String PROJECT_NAME = "proj5";
        final String USER_ID = "1";

        final CreateProjectOutputBoundary presenter = new CreateProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateProjectOutputData output) {
                assertEquals(output.getProjectName(), PROJECT_NAME);
                try {
                    String projectId = null;
                    boolean projectCreated = false;
                    for (Project project : ((InMemoryAPIDataAccessObject) apidao).getProjects()) {
                        if (project.getProjectName().equals(PROJECT_NAME)) {
                            projectId = project.getProjectID();
                            projectCreated = true;
                            break;
                        }
                    }
                    assertTrue(projectCreated);
                    String finalProjectId = projectId;
                    assertTrue(
                            ((InMemorySQLDataAccessObject) sqldao).permissions.stream()
                                    .anyMatch(
                                            (List<String> e) -> e.get(1).equals(USER_ID) &&
                                                    e.get(2).equals(finalProjectId)
                                    )
                    );
                } catch (Exception e) {
                    fail("exception in successTest ApiDAO getProjects(), how this happen?");
                }
            }

            @Override
            public void prepareFailView(String errMessage) {
                fail("fail in successTest, how this happen?");
            }
        };

        CreateProjectInputData input = new CreateProjectInputData(PROJECT_NAME, "1");
        final CreateProjectInputBoundary interactor = new CreateProjectInteractor(presenter, apidao, sqldao);
        interactor.execute(input);
    }

    @Test
    public void emptyNameTest() {
        final String PROJECT_NAME = "";
        final String USER_ID = "1";

        final CreateProjectOutputBoundary presenter = new CreateProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateProjectOutputData output) {
                fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        CreateProjectInputData input = new CreateProjectInputData(PROJECT_NAME, USER_ID);
        final CreateProjectInputBoundary interactor = new CreateProjectInteractor(presenter, apidao, sqldao);
        interactor.execute(input);
    }

    @Test
    public void DAOExceptionTest() {
        final String PROJECT_NAME = "test dao failure";
        final String USER_ID = "1";

        final CreateProjectOutputBoundary presenter = new CreateProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateProjectOutputData output) {
                fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        CreateProjectInputData input = new CreateProjectInputData(PROJECT_NAME, USER_ID);
        final CreateProjectInputBoundary interactor = new CreateProjectInteractor(presenter, apidao, sqldao);
        interactor.execute(input);
    }
}