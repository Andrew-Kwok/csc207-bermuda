package data_access.in_memory;


import domains.project.entity.Project;
import domains.project.use_case.create_project.CreateProjectApiDataAccessInterface;
import domains.project.use_case.delete_project.DeleteProjectApiDataAccessInterface;
import domains.project.use_case.edit_project.EditProjectDataAccessInterface;
import domains.project.use_case.get_project.GetProjectApiDataAccessInterface;
import domains.task.entity.Task;
import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.close_task.CloseTaskDataAccessInterface;
import domains.task.use_case.edit_task.EditTaskDataAccessInterface;
import domains.task.use_case.get_task.GetTaskDataAccessInterface;

import java.util.*;
public class InMemoryAPIDataAccessObject implements
        CreateProjectApiDataAccessInterface, GetProjectApiDataAccessInterface,
        EditProjectDataAccessInterface, DeleteProjectApiDataAccessInterface {
    private int numProject = 1;
    /** projects map projectID to projectName for all initiated objects.
     */
    private final Map<String, String> projects = new HashMap<>();

    @Override
    public String createProject(String projectName) throws Exception {
        if (projectName.equals("test dao failure"))
            throw new Exception("failed successfully");
        projects.put(String.valueOf(numProject), projectName);
        return String.valueOf(numProject++);
    }

    @Override
    public List<Project> getProjects() throws Exception {
        List<Project> res = new ArrayList<>();
        for (Map.Entry<String, String> project : projects.entrySet()) {
            res.add(Project.builder()
                    .projectID(project.getKey())
                    .projectName(project.getValue())
                    .build()
            );
        }
        return res;
    }

    @Override
    public void editProject(Project project) throws Exception {
        if (project.getProjectID().equals("test dao failure"))
            throw new Exception("failed successfully");
        projects.put(project.getProjectID(), project.getProjectName());
    }

    @Override
    public void deleteProject(String projectId) throws Exception {
        if (projectId.equals("test dao failure"))
            throw new Exception("failed successfully");
        projects.remove(projectId);
    }
}
