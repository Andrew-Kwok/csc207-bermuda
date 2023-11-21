package domains.project.use_case.get_project;

import domains.permission.entity.Permission;

import java.util.List;

public interface GetProjectSqlDataAccessInterface {
    List<Permission> getPermissions(String userId) throws Exception;
}
