package data_access.in_memory;


import domains.project.entity.Project;
import domains.project.use_case.create_project.CreateProjectApiDataAccessInterface;
import domains.project.use_case.get_project.GetProjectApiDataAccessInterface;
import domains.task.entity.Task;
import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.close_task.CloseTaskDataAccessInterface;
import domains.task.use_case.edit_task.EditTaskDataAccessInterface;
import domains.task.use_case.get_task.GetTaskDataAccessInterface;

import java.util.*;
public class InMemoryAPIDataAccessObject implements
        CreateProjectApiDataAccessInterface, GetProjectApiDataAccessInterface {
    private final Map<String, ?> projects

    @Override
    public String createProject(String projectName) throws Exception {
        return null;
    }

    @Override
    public List<Project> getProjects() throws Exception {
        return null;
    }
}
