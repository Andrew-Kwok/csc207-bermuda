package domains.task.use_case.add_task;

import app.task.AddTaskUseCaseFactory;
import config.Config;
import data_access.todoist.ApiDataAccessObject;
import domains.task.entity.NewTaskFactory;
import domains.task.entity.Task;
import interface_adapter.task.add_task.AddTaskPresenter;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.task.get_task.GetTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AddTaskInteractorTest {

    @org.junit.Before
    public void setUp() throws Exception {

        ApiDataAccessObject apiDataAccessObject = new ApiDataAccessObject(
                Config.getEnv("TODOIST_API_URL"),
                Config.getEnv("TODOIST_API_TEST_TOKEN")
        );

        AddTaskDataAccessInterface taskRepository = apiDataAccessObject;

        NewTaskFactory taskFactory = new NewTaskFactory();
        Task task = taskFactory.create("0", "project0", "name0", "desc0");
        AddTaskInputData inputData = new AddTaskInputData(task.getProjectID(), task.getTaskName(), task.getTaskDescription());

        // Make a presenter here that asserts things
        // This instantiates an anonymous SignupOutputBoundary implementing class
        AddTaskOutputBoundary successPresenter = new AddTaskOutputBoundary() {
            @Override
            public void prepareSuccessView(AddTaskOutputData user) {
                assertEquals("0", task.getTaskID());
                assertEquals("project0", task.getProjectID());
                assertEquals("name0", task.getTaskName());
                assertEquals("desc0", task.getTaskDescription());
                //assertTrue(userRepository.existsByName("Paul"));
            }
            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };


        AddTaskInputBoundary interactor = new AddTaskInteractor(successPresenter, taskRepository);
        interactor.execute(inputData); // This will eventually send Output Data to the successPresenter
    }

    @org.junit.After
    public void tearDown() throws Exception {
      /*  try {
            sqlDAO.clearAllPermissions();
            sqlDAO.clearAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/
    }
}