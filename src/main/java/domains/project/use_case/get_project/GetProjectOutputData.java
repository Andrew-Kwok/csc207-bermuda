package domains.project.use_case.get_project;

import java.util.List;
import java.util.Map;
public class GetProjectOutputData {
    private final Map<String, List<String>> projectPermissions;

    public GetProjectOutputData(Map<String, List<String>> projectPermissions) {
        this.projectPermissions = projectPermissions;
    }

    public Map<String, List<String>> getProjectPermissions() { return projectPermissions; }
}
