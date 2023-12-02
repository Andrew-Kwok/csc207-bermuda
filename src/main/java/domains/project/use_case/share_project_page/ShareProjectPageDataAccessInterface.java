package domains.project.use_case.share_project_page;

import java.util.List;
public interface ShareProjectPageDataAccessInterface {
    List<List<String>> getUsersNameAndId(String projectId) throws Exception;
}
