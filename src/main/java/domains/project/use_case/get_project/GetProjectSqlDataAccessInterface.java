package domains.project.use_case.get_project;

import java.util.List;

import domains.permission.entity.Permission;
public interface GetProjectSqlDataAccessInterface {
    List<Permission> getPermissions(String userId);

}
