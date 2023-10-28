package domains.permission.entity;

import utils.uuid;

public class NewPermissionFactory implements PermissionFactory{
    @Override
    public Permission create(String projectID, String userID, String permissionName, String permissionDescription, String access) {
        String permissionID = uuid.newUUID();
        return new Permission(permissionID, projectID, userID, permissionName, permissionDescription, access);
    }
}
