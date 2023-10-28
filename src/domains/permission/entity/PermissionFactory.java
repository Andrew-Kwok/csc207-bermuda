package domains.permission.entity;

public interface PermissionFactory {
    Permission create(String projectID, String userID, String permissionName, String permissionDescription, String access);
}
