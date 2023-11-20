package domains.project.use_case.create_project;

import domains.permission.entity.Permission;

public interface CreateProjectSqlDataAccessInterface {
    void createPermission(Permission permission) throws Exception;
}
