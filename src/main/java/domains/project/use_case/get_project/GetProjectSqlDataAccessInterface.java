package domains.project.use_case.get_project;

import domains.project.entity.Project;

import java.util.List;

public interface GetProjectSqlDataAccessInterface {
    List<Project> getProjects(String projectID) throws Exception;
}
