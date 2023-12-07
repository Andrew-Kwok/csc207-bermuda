package domains.task.use_case.get_task;

import domains.task.entity.Task;
import domains.task.use_case.InMemoryDAO;
import org.junit.Test;

import java.util.Map;

import static constant.ViewConstant.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetTaskInteractorTest {
    private final Task task = new Task("0000000000", "2324643105", "task0", "desc0");;

    @Test
    public void successTest() {
        GetTaskOutputBoundary successPresenter = new GetTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(GetTaskOutputData getTaskOutputData) {
                assertEquals(1, getTaskOutputData.getTasks().size());
                for (Map<String, String> item : getTaskOutputData.getTasks()) {
                    assertEquals(item.get(TASK_ID), task.getTaskID());
                    assertEquals(item.get(PROJECT_ID), task.getProjectID());
                    assertEquals(item.get(TASK_NAME), task.getTaskName());
                    assertEquals(item.get(TASK_DESCRIPTION), task.getTaskDescription());
                }
            }
            @Override
            public void prepareFailView(String error) {
                System.out.println("CloseTaskInteractorTest.TestCloseTaskPresenter.prepareFailView");
                System.out.println(error);
                fail();
            }
        };

        InMemoryDAO inMemoryDAO = new InMemoryDAO();
        try {
            inMemoryDAO.addTask(task);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }

        GetTaskInteractor getTaskInteractor = new GetTaskInteractor(successPresenter, inMemoryDAO);
        getTaskInteractor.execute(new GetTaskInputData(task.getProjectID()));
    }

    @Test
    public void nullInputTest() {
        GetTaskOutputBoundary failPresenter = new GetTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(GetTaskOutputData getTaskOutputData) {
                fail();
            }
            @Override
            public void prepareFailView(String error) {
                assertEquals("Input data is null", error);
            }
        };

        InMemoryDAO inMemoryDAO = new InMemoryDAO();

        GetTaskInteractor getTaskInteractor = new GetTaskInteractor(failPresenter, inMemoryDAO);
        getTaskInteractor.execute(null);
    }

    @Test
    public void nullProjectIdTest() {
        GetTaskOutputBoundary failPresenter = new GetTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(GetTaskOutputData getTaskOutputData) {
                fail();
            }
            @Override
            public void prepareFailView(String error) {
                assertEquals("Project ID is missing", error);
            }
        };

        InMemoryDAO inMemoryDAO = new InMemoryDAO();

        GetTaskInteractor getTaskInteractor = new GetTaskInteractor(failPresenter, inMemoryDAO);
        getTaskInteractor.execute(new GetTaskInputData(null));
    }

    @Test
    public void nullDAOTest() {
        GetTaskOutputBoundary failPresenter = new GetTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(GetTaskOutputData getTaskOutputData) {
                fail();
            }
            @Override
            public void prepareFailView(String error) {
                assertEquals("Cannot invoke \"domains.task.use_case.get_task.GetTaskDataAccessInterface.getTasks(String)\" because \"this.getTaskDataAccess\" is null", error);
            }
        };

        GetTaskInteractor getTaskInteractor = new GetTaskInteractor(failPresenter, null);
        getTaskInteractor.execute(new GetTaskInputData(task.getProjectID()));

    }
}
