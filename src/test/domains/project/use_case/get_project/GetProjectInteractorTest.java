package domains.project.use_case.get_project;

import data_access.in_memory.InMemoryAPIDataAccessObject;
import data_access.in_memory.InMemorySQLDataAccessObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static constant.ViewConstant.PROJECT_ID;
import static constant.ViewConstant.PROJECT_NAME;
import static org.junit.jupiter.api.Assertions.*;

class GetProjectInteractorTest {
    public static GetProjectSqlDataAccessInterface sqldao;
    public static GetProjectApiDataAccessInterface apidao;

    @BeforeAll
    public static void initiateDAOs() {
        InMemoryAPIDataAccessObject imapidao = new InMemoryAPIDataAccessObject();
        InMemorySQLDataAccessObject imsqldao = new InMemorySQLDataAccessObject();
        for (int i = 1; i < 5; i++) {
            String str_i = String.valueOf(i);
            imsqldao.permissions.add(List.of(str_i, str_i, str_i, "owner", ""));
            imsqldao.users.add(List.of(str_i, "test" + str_i, "pass" + str_i, "0"));
        }
        imsqldao.permissions.add(List.of("6", "2", "1", "editor", ""));
        sqldao = imsqldao;

        for (int i = 1; i < 5; i++) {
            try {
                imapidao.createProject("proj" + String.valueOf(i));
            } catch (Exception e) {
                System.out.println("error in initiation, how this happen?");
            }

            apidao = imapidao;
        }
    }

    @Test
    public void successTest() {
        final String USER_ID = "2";

        GetProjectOutputBoundary presenter = new GetProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(GetProjectOutputData output) {
                Set<String> outputIds = output.getProjects()
                        .stream()
                        .map((Map<String, String> e) -> e.get(PROJECT_ID))
                        .collect(Collectors.toSet());
                Set<String> outputNames = output.getProjects()
                        .stream()
                        .map((Map<String, String> e) -> e.get(PROJECT_NAME))
                        .collect(Collectors.toSet());

                assertEquals(outputIds, Set.of("1", "2"));
                assertEquals(outputNames, Set.of("proj1", "proj2"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("fail view not expected");
            }
        };

        GetProjectInputData input = new GetProjectInputData(USER_ID);
        GetProjectInteractor interactor = new GetProjectInteractor(presenter, sqldao, apidao);
        interactor.execute(input);
    }

    @Test
    public void DAOExceptionTest() {
        final String USER_ID = "test dao failure";
        GetProjectOutputBoundary presenter = new GetProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(GetProjectOutputData output) {
                fail("success view not expected");
            }

            @Override
            public void prepareFailView(String error) {
                assertNotNull(error);
            }
        };

        GetProjectInputData input = new GetProjectInputData(USER_ID);
        GetProjectInteractor interactor = new GetProjectInteractor(presenter, sqldao, apidao);
        interactor.execute(input);
    }
}