package domains.task.use_case.edit_task;

import domains.task.entity.Task;
import domains.task.use_case.InMemoryDAO;
import domains.task.use_case.add_task.*;
import domains.task.use_case.close_task.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EditTaskInteractorTest {
    private InMemoryDAO inMemoryDAO;
    private Task task;
    EditTaskInputBoundary editTaskInteractor;
    EditTaskDataAccessInterface taskRepository;

    @org.junit.Before
    public void setUp() throws Exception {

        inMemoryDAO = new InMemoryDAO();

        taskRepository = inMemoryDAO;

        task = new Task("0000000000", "2324643105", "task0", "desc0");
        inMemoryDAO.addTask(task);
        EditTaskInputData inputData = new EditTaskInputData(task.getTaskID(), task.getProjectID(), task.getTaskName(), task.getTaskDescription());


        // Make a presenter here that asserts things
        // This instantiates an anonymous SignupOutputBoundary implementing class
        EditTaskOutputBoundary successPresenter = new EditTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(EditTaskOutputData editTaskOutputData) {
                assertEquals(editTaskOutputData.getTaskID(), task.getTaskID());
                System.out.println(task.getTaskID());
            }
            @Override
            public void prepareFailView(String error) {
                System.out.println("EditTaskInteractorTest.TestEditTaskPresenter.prepareFailView");
                System.out.println(error);
                Assertions.fail();
            }
        };


        editTaskInteractor = new EditTaskInteractor(successPresenter, taskRepository);
        editTaskInteractor.execute(inputData); // This will eventually send Output Data to the successPresenter
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
        EditTaskInputData editTaskInputData = new EditTaskInputData(task.getTaskID(),task.getProjectID(), task.getTaskName(), task.getTaskDescription());

        editTaskInteractor.execute(editTaskInputData);
    }

    @Test
    public void nullProjIDTest() {
        final EditTaskOutputBoundary presenter = new EditTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(EditTaskOutputData editTaskOutputData) {
                Assertions.fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        EditTaskInputData input = new EditTaskInputData(task.getTaskID(),null, task.getTaskName(), task.getTaskDescription());
        final EditTaskInputBoundary interactor = new EditTaskInteractor(presenter, taskRepository);
        interactor.execute(input);
    }

    @Test
    public void nullInputTest() {
        final EditTaskOutputBoundary presenter = new EditTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(EditTaskOutputData output) {
                Assertions.fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        final EditTaskInputBoundary interactor = new EditTaskInteractor(presenter, taskRepository);
        interactor.execute(null);
    }

    @Test
    public void nullNameTest() {
        final EditTaskOutputBoundary presenter = new EditTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(EditTaskOutputData output) {
                Assertions.fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        EditTaskInputData input = new EditTaskInputData(task.getTaskID(),task.getProjectID(), null, task.getTaskDescription());
        final EditTaskInputBoundary interactor = new EditTaskInteractor(presenter, taskRepository);
        interactor.execute(input);
    }

    @Test
    public void nullTaskIdTest() {
        final EditTaskOutputBoundary presenter = new EditTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(EditTaskOutputData output) {
                Assertions.fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        EditTaskInputData input = new EditTaskInputData(null,task.getProjectID(), task.getTaskName(), task.getTaskDescription());
        final EditTaskInputBoundary interactor = new EditTaskInteractor(presenter, taskRepository);
        interactor.execute(input);
    }
}
