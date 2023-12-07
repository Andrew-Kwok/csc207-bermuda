package domains.task.use_case.add_task;

import domains.task.use_case.InMemoryDAO;
import domains.task.entity.Task;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class AddTaskInteractorTest {
    private InMemoryDAO inMemoryDAO;
    private Task task;
    AddTaskInputBoundary addTaskInteractor;
    AddTaskDataAccessInterface taskRepository;

    @org.junit.Before
    public void setUp() throws Exception {

        inMemoryDAO = new InMemoryDAO();

        taskRepository = inMemoryDAO;

        task = new Task(null, "2324643105", "task0", "desc0");
        inMemoryDAO.addTask(task);
        AddTaskInputData inputData = new AddTaskInputData(task.getProjectID(), task.getTaskName(), task.getTaskDescription());


        // Make a presenter here that asserts things
        // This instantiates an anonymous SignupOutputBoundary implementing class
        AddTaskOutputBoundary successPresenter = new AddTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(AddTaskOutputData addTaskOutputData) {
                assertEquals(addTaskOutputData.getTaskName(), task.getTaskName());
                System.out.println(task.getTaskID());
            }
            @Override
            public void prepareFailView(String error) {
                System.out.println("AddTaskInteractorTest.TestAddTaskPresenter.prepareFailView");
                System.out.println(error);
                fail();
            }
        };


        addTaskInteractor = new AddTaskInteractor(successPresenter, taskRepository);
        addTaskInteractor.execute(inputData); // This will eventually send Output Data to the successPresenter
        //addTaskInteractor.execute(noProjectIdInput);
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
        AddTaskInputData addTaskInputData = new AddTaskInputData(task.getProjectID(), task.getTaskName(), task.getTaskDescription());

        addTaskInteractor.execute(addTaskInputData);
    }

    @Test
    public void nullProjIDTest() {
        //AddTaskInputData noProjectIdInput = new AddTaskInputData(null, task.getTaskName(), task.getTaskDescription());

        final AddTaskOutputBoundary presenter = new AddTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(AddTaskOutputData output) {
                fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        AddTaskInputData input = new AddTaskInputData(null, task.getTaskName(), task.getTaskDescription());
        final AddTaskInputBoundary interactor = new AddTaskInteractor(presenter, taskRepository);
        interactor.execute(input);
    }

    @Test
    public void nullInputTest() {
        final AddTaskOutputBoundary presenter = new AddTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(AddTaskOutputData output) {
                fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        final AddTaskInputBoundary interactor = new AddTaskInteractor(presenter, taskRepository);
        interactor.execute(null);
    }

    @Test
    public void nullNameTest() {
        final AddTaskOutputBoundary presenter = new AddTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(AddTaskOutputData output) {
                fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        AddTaskInputData input = new AddTaskInputData(task.getProjectID(), null, task.getTaskDescription());
        final AddTaskInputBoundary interactor = new AddTaskInteractor(presenter, taskRepository);
        interactor.execute(input);
    }

    @Test
    public void DAOExceptionTest() {
        final AddTaskOutputBoundary presenter = new AddTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(AddTaskOutputData output) {
                fail("success view not expected");
            }

            @Override
            public void prepareFailView(String errMessage) {
                assertNotNull(errMessage);
            }
        };

        AddTaskInputData input = new AddTaskInputData(task.getProjectID(), task.getTaskName(), task.getTaskDescription());
        final AddTaskInputBoundary interactor = new AddTaskInteractor(presenter, null);
        interactor.execute(input);
    }
}