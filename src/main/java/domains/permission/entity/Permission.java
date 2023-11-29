package domains.permission.entity;

import domains.task.entity.Task;

import java.util.Map;

import static constant.ViewConstant.*;

public class Permission {
    private final String permissionID;
    private final String projectID;
    private final String userID;
    private String permissionName;
    private String permissionDescription = "";

    public Permission(String permissionID, String projectID, String userID, String permissionName, String permissionDescription) {
        this.permissionID = permissionID;
        this.projectID = projectID;
        this.userID = userID;
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
    }

    public Permission(String permissionID, String projectID, String userID, String permissionName) {
        this.permissionID = permissionID;
        this.projectID = projectID;
        this.userID = userID;
        this.permissionName = permissionName;
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

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public static permissionBuilder builder() {
        return new permissionBuilder();
    }

    public static class permissionBuilder {
        private String permissionID;
        private String projectID;
        private String userID;
        private String permissionName;
        private String permissionDescription = "";

        permissionBuilder() {
        }

        public permissionBuilder permissionID(String permissionID) {
            this.permissionID = permissionID;
            return this;
        }

        public permissionBuilder projectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public permissionBuilder userID(String userID) {
            this.userID = userID;
            return this;
        }

        public permissionBuilder permissionName(String permissionName) {
            this.permissionName = permissionName;
            return this;
        }

        public permissionBuilder permissionDescription(String permissionDescription) {
            this.permissionDescription = permissionDescription;
            return this;
        }

        public Permission build() {
            return new Permission(permissionID, projectID, userID, permissionName, permissionDescription);
        }
    }

    @Override
    public String toString() {
        return ("""
                [Permission ID: %s]
                \t Project ID: %s
                \t User ID: %s
                \t Permission Name: %s
                \t Permission Description: %s
                """).
                formatted(permissionID, projectID, userID, permissionName, permissionDescription);
    }

    public Map<String, String> toMap() {
        return Map.of(
                PERMISSION_ID, permissionID,
                PROJECT_ID, projectID,
                USER_ID, userID,
                PERMISSION_NAME, permissionName,
                PERMISSION_DESCRIPTION, permissionDescription
        );
    }
}
