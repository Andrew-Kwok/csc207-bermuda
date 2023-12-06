package domains.task.use_case.close_task;

import domains.project.entity.Project;
import domains.task.entity.Task;
import domains.task.use_case.add_task.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CloseTaskInteractorTest {
    private InMemoryDAO inMemoryDAO;
    private String taskID;
    private Task task;
    CloseTaskInputBoundary closeTaskInteractor;

    @org.junit.Before
    public void setUp() throws Exception {

        inMemoryDAO = new InMemoryDAO();

        CloseTaskDataAccessInterface taskRepository = inMemoryDAO;

        task = new Task(null, "2324643105", "task0", "desc0");
        inMemoryDAO.addTask(task);

        // Make a presenter here that asserts things
        // This instantiates an anonymous SignupOutputBoundary implementing class
        CloseTaskOutputBoundary successPresenter = new CloseTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(CloseTaskOutputData closeTaskOutputData) {
                assertEquals(closeTaskOutputData.getTaskID(), task.getTaskID());
            }
            @Override
            public void prepareFailView(String error) {
                System.out.println("AddTaskInteractorTest.TestAddTaskPresenter.prepareFailView");
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
    public void testAddTask() {
        AddTaskInputData addTaskInputData = new AddTaskInputData(task.getProjectID(), task.getTaskName(), task.getTaskDescription());

        addTaskInteractor.execute(addTaskInputData);
    }
}
