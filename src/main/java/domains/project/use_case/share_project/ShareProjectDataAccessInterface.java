package domains.project.use_case.share_project;

import domains.permission.entity.Permission;

public interface ShareProjectDataAccessInterface {
    void createPermission(Permission newPermission) throws Exception;
}
