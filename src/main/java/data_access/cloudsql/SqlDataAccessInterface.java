package data_access.cloudsql;

import domains.permission.entity.Permission;

import java.util.List;

public interface SqlDataAccessInterface {
    List<Permission> getPermissions(String permissionID) throws Exception;
}
