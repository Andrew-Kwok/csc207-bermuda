package domains.project.use_case.edit_project;

import data_access.in_memory.*;
import domains.project.entity.Project;
import static constant.ViewConstant.*;

import domains.project.use_case.get_project.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
class EditProjectInteractorTest {
    public static EditProjectDataAccessInterface dao;

    @BeforeAll
    public static void initiateDAO() {
        InMemoryAPIDataAccessObject imapidao = new InMemoryAPIDataAccessObject();
        for (int i = 1; i < 5; i++) {
            try {
                imapidao.createProject("proj" + String.valueOf(i));
            } catch (Exception e) {
                System.out.println("error in initiation, how this happen?");
            }
        }
        dao = imapidao;
    }

    @Test
    public void successTest() {
        final String PROJECT_ID = "1";
        final String PROJECT_NAME = "proj1";
        final String NEW_PROJECT_NAME = "new-proj1";

        final EditProjectOutputBoundary presenter = new EditProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(EditProjectOutputData output) {
                assertEquals(output.getProjectID(), PROJECT_ID);
                try {
                assertTrue(
                        ((InMemoryAPIDataAccessObject) dao).getProjects().stream()
                            .anyMatch(
                                (Project e) -> e.getProjectID().equals(PROJECT_ID) &&
                                        e.getProjectName().equals(NEW_PROJECT_NAME)
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

        final EditProjectInputData input = new EditProjectInputData(PROJECT_ID, NEW_PROJECT_NAME);
        final EditProjectInteractor interactor = new EditProjectInteractor(presenter, dao);
        interactor.execute(input);
    }

    @Test
    public void DAOExceptionTest() {
        final String PROJECT_ID = "test dao failure";
        final String PROJECT_NAME = "proj1";
        final String NEW_PROJECT_NAME = "new-proj1";
        final EditProjectOutputBoundary presenter = new EditProjectOutputBoundary() {
            @Override
            public void prepareSuccessView(EditProjectOutputData output) {
                fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        final EditProjectInputData input = new EditProjectInputData(PROJECT_ID, NEW_PROJECT_NAME);
        final EditProjectInteractor interactor = new EditProjectInteractor(presenter, dao);
        interactor.execute(input);
    }
}