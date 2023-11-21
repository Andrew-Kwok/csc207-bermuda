package domains.project.use_case.get_project;

import domains.project.entity.Project;

import java.util.List;

public class GetProjectOutputData {
    private final List<Project> projects;

    public GetProjectOutputData(List<Project> projects) {
        this.projects = projects;
    }

    public List<Project> getprojects() {
        return projects;
    }
}
