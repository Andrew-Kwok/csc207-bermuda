/*
package domains.task.use_case.get_task;

import domains.task.entity.Task;
import domains.task.use_case.InMemoryDAO;
import domains.task.use_case.close_task.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetTaskInteractorTest {
    private InMemoryDAO inMemoryDAO;
    private Task task;
    GetTaskInputBoundary getTaskInteractor;
    GetTaskInputData inputData;
    GetTaskDataAccessInterface taskRepository;

    @org.junit.Before
    public void setUp() throws Exception {

        inMemoryDAO = new InMemoryDAO();

        taskRepository = inMemoryDAO;

        task = new Task("0000000000", "2324643105", "task0", "desc0");
        inMemoryDAO.addTask(task);
        inputData = new GetTaskInputData(task.getProjectID());

        // Make a presenter here that asserts things
        // This instantiates an anonymous SignupOutputBoundary implementing class
        GetTaskOutputBoundary successPresenter = new GetTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(GetTaskOutputData getTaskOutputData) {
                assertEquals(getTaskOutputData.getTasks(), task.getTaskID());

            }
            @Override
            public void prepareFailView(String error) {
                System.out.println("CloseTaskInteractorTest.TestCloseTaskPresenter.prepareFailView");
                System.out.println(error);
                fail();
            }
        };


        closeTaskInteractor = new CloseTaskInteractor(successPresenter, taskRepository);
        closeTaskInteractor.execute(inputData); // This will eventually send Output Data to the successPresenter
    }

    @org.junit.After
    public void tearDown() throws Exception {
        try {
            List<Task> tasks = inMemoryDAO.getTasks("2324643105");
            for (Task task : tasks) {
                inMemoryDAO.closeTask(task.getTaskID());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void successTest() {
        closeTaskInteractor.execute(inputData);
    }

    @Test
    public void nullInputTest() {
        final CloseTaskOutputBoundary presenter = new CloseTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(CloseTaskOutputData output) {
                Assertions.fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };
        final CloseTaskInputBoundary interactor = new CloseTaskInteractor(presenter, taskRepository);
        interactor.execute(null);
    }

}
*/
