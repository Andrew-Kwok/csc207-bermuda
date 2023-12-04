package domains.project.use_case.edit_project;

import domains.project.entity.Project;

public interface EditProjectDataAccessInterface {

    void editProject(Project project) throws Exception;
}