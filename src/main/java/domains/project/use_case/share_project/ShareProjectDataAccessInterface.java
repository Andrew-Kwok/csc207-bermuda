package domains.project.use_case.share_project;

import domains.permission.entity.Permission;

public interface ShareProjectDataAccessInterface {
    void savePermission(Permission newPermission) throws Exception;
}
