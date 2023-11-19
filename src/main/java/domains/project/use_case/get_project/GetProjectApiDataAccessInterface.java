package domains.project.use_case.get_project;

import domains.project.entity.Project;
public interface GetProjectApiDataAccessInterface {
    Project getProject(String projectId);
}
