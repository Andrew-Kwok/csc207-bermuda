package domains.project.use_case.delete_project;

public interface DeleteProjectSqlDataAccessInterface {
    void deletePermissionByProjectId(String projectId) throws Exception;
}