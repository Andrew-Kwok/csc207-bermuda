package entity.permission;

public interface PermissionFactory {
    Permission create(String projectID, String userID, String permissionName, String permissionDescription, String access);
}
