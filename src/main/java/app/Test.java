package app;

// import data_access.todoist.ApiDataAccessInterface;
import data_access.todoist.ApiDataAccessObject;
import domains.project.entity.Project;
import domains.task.entity.Task;
import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.add_task.AddTaskInputData;
import domains.task.use_case.add_task.AddTaskInteractor;
import domains.task.use_case.add_task.AddTaskOutputBoundary;
import interface_adapter.task.add_task.AddTaskPresenter;
import interface_adapter.task.add_task.AddTaskViewModel;
import interface_adapter.view_model.ViewManagerModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Test {
    public static void main(String[] args) {
//        ApiDataAccessInterface api = new ApiDataAccessObject();
//        Project project = api.getProject("2320965999");
//
//        System.out.println(project);

        AddTaskDataAccessInterface task_api = new ApiDataAccessObject();
        Task task = new Task("0000000000", "2320965999", "test", "content-1");

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        AddTaskViewModel addtaskViewModel = new AddTaskViewModel();
        AddTaskDataAccessInterface addTaskDAO = new ApiDataAccessObject();
//        AddTaskOutputBoundary addTaskOutBound = new AddTaskPresenter(viewManagerModel, addtaskViewModel, addTaskDAO);
//        AddTaskInteractor addTaskInteractor = new AddTaskInteractor(addTaskDAO, addTaskOutBound);
//
//        LocalDateTime dateTime = LocalDate.of(2024, Month.JANUARY, 18).atStartOfDay();
//        AddTaskInputData addTaskInputData = new AddTaskInputData("task-1", "test-1", dateTime, "2320965999");
//        addTaskInteractor.execute(addTaskInputData);

    }
}
