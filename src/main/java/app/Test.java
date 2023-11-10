package app;

import data_access.todoist.ApiDataAccessInterface;
import data_access.todoist.ApiDataAccessObject;
import domains.project.entity.Project;
import domains.task.entity.Task;
import domains.task.use_case.add_task.AddTaskDataAccessInterface;

public class Test {
    public static void main(String[] args) {
//        ApiDataAccessInterface api = new ApiDataAccessObject();
//        Project project = api.getProject("2320965999");
//
//        System.out.println(project);

        AddTaskDataAccessInterface task_api = new ApiDataAccessObject();
        Task task = new Task("0000000000", "2320965999", "test");
        task.setTaskDescription("content-1");
        String taskID = task_api.addTask("2320965999", task);

        System.out.println(taskID);
    }
}
