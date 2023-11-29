package domains.project.use_case.get_project;

import java.util.List;
import java.util.Map;

public class GetProjectOutputData {
    private final List<Map<String, String>> projects;

    public GetProjectOutputData(List<Map<String, String>> projects) {
        this.projects = projects;
    }

    public List<Map<String, String>> getProjects() {
        return projects;
    }
}
