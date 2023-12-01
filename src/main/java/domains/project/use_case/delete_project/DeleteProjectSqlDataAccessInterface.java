package domains.project.use_case.delete_project;

import domains.permission.entity.Permission;

import java.util.List;

public interface DeleteProjectSqlDataAccessInterface {
    void deletePermissionByProjectId(String projectId) throws Exception;
}