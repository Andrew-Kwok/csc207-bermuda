package entity.permission;

public class Permission {
    private final String permissionID;
    private final String projectID;
    private final String userID;
    private final String permissionName;
    private final String permissionDescription;
    private final String access;

    public Permission(String permissionID, String projectID, String userID, String permissionName, String permissionDescription, String access) {
        this.permissionID = permissionID;
        this.projectID = projectID;
        this.userID = userID;
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
        this.access = access;
    }

    public String getPermissionID() {
        return permissionID;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getUserID() {
        return userID;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public String getAccess() {
        return access;
    }

    @Override
    public String toString() {
        return ("""
                [Permission ID: %s]
                \t Project ID: %s
                \t User ID: %s
                \t Permission Name: %s
                \t Permission Description: %s
                \t Access: %s
                """).
                formatted(permissionID, projectID, userID, permissionName, permissionDescription, access);
    }
}
