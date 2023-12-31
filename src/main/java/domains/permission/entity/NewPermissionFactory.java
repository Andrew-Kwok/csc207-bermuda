package domains.permission.entity;

import utils.uuid;

public class NewPermissionFactory {
    public static Permission create(String projectID, String userID, String permissionName, String permissionDescription) {
        String permissionID = uuid.newUUID();
        return new Permission(permissionID, projectID, userID, permissionName, permissionDescription);
    }
}
