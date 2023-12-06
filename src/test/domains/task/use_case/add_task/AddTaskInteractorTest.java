package domains.task.use_case.add_task;

import domains.project.entity.Project;
import domains.task.entity.Task;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddTaskInteractorTest {
    //private ApiDataAccessObject apiDataAccessObject;
    private InMemoryDAO inMemoryDAO;
    private Project project;
    private Task task;
    AddTaskInputBoundary addTaskInteractor;

    @org.junit.Before
    public void setUp() throws Exception {

        inMemoryDAO = new InMemoryDAO();

        AddTaskDataAccessInterface taskRepository = inMemoryDAO;

        task = new Task(null, "2324643105", "task0", "desc0");
        inMemoryDAO.addTask(task);
        AddTaskInputData inputData = new AddTaskInputData(task.getProjectID(), task.getTaskName(), task.getTaskDescription());

        // Make a presenter here that asserts things
        // This instantiates an anonymous SignupOutputBoundary implementing class
        AddTaskOutputBoundary successPresenter = new AddTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(AddTaskOutputData addTaskOutputData) {
                (task.getTaskID()).isEmpty();
                assertEquals("2324643105", task.getProjectID());
                assertEquals("task0", task.getTaskName());
                assertEquals("desc0", task.getTaskDescription());
            }
            @Override
            public void prepareFailView(String error) {
                System.out.println("AddTaskInteractorTest.TestAddTaskPresenter.prepareFailView");
                System.out.println(error);
                fail("Use case failure is unexpected.");
            }
        };


        addTaskInteractor = new AddTaskInteractor(successPresenter, taskRepository);
        addTaskInteractor.execute(inputData); // This will eventually send Output Data to the successPresenter
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
    public void testAddTask() {
        AddTaskInputData addTaskInputData = new AddTaskInputData(null, task.getTaskName(), task.getTaskDescription());

        addTaskInteractor.execute(addTaskInputData);
    }
}