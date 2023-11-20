package domains.project.use_case.get_project;

import domains.project.entity.Project;
import java.util.List;
public interface GetProjectApiDataAccessInterface {
    List<Project> getProject(String projectID) throws Exception;

}
