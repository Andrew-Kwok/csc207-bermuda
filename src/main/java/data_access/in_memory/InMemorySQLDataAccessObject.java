package data_access.in_memory;

import domains.permission.entity.NewPermissionFactory;
import domains.permission.entity.Permission;
import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.permission.use_case.delete_permission.DeletePermissionDataAccessInterface;
import domains.permission.use_case.get_permission.GetPermissionDataAccessInterface;
import domains.permission.use_case.update_permission.UpdatePermissionDataAccessInterface;
import domains.project.use_case.create_project.CreateProjectSqlDataAccessInterface;
import domains.project.use_case.delete_project.DeleteProjectSqlDataAccessInterface;
import domains.project.use_case.get_project.GetProjectSqlDataAccessInterface;
import domains.project.use_case.share_project.ShareProjectDataAccessInterface;
import domains.project.use_case.share_project_page.ShareProjectPageDataAccessInterface;
import domains.user.entity.User;
import domains.user.use_case.login.LoginUserDataAccessInterface;
import domains.user.use_case.signup.SignupUserDataAccessInterface;

import java.util.*;
public class InMemorySQLDataAccessObject implements
        ShareProjectDataAccessInterface, ShareProjectPageDataAccessInterface,
        GetProjectSqlDataAccessInterface, DeleteProjectSqlDataAccessInterface {
    /** permissions index in order from 0 to 4:
     * - permissionID
     * - userID
     * - projectID
     * - permissionName
     * - permissionDescription
    */
    public final List<List<String>> permissions = new ArrayList<>();

    /** users index in order from 0 to 4:
     * - userID
     * - userName
     * - password
     * - user_level
     */
    public final List<List<String>> users = new ArrayList<>();

    public InMemorySQLDataAccessObject() {}

    @Override
    public void createPermission(Permission newPermission) throws Exception {
        if (newPermission.getProjectID().equals("test dao failure")) {
            throw new Exception("failed successfully");
        }
        permissions.add(List.of(newPermission.getPermissionID(),
                newPermission.getUserID(), newPermission.getProjectID(),
                newPermission.getPermissionName(), newPermission.getPermissionDescription()));
    }

    @Override
    public List<List<String>> getUsersNameAndId(String projectId) throws Exception {
        if (projectId.equals("test dao failure")) {
            throw new Exception("failed successfully");
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> user : users) {
            boolean hasPermission = false;
            for (List<String> permission : permissions) {
                if (permission.get(2).equals(projectId) &&
                        user.get(0).equals(permission.get(1))) {
                    hasPermission = true;
                    break;
                    }
                }
            if (!hasPermission) {
                res.add(List.of(user.get(1), user.get(0)));
            }
        }
        return res;
    }

    @Override
    public List<Permission> getPermissions(String userId) throws Exception {
        if (userId.equals("test dao failure")) {
            throw new Exception("failed successfully");
        }
        List<Permission> res = new ArrayList<>();
        for (List<String> permission : permissions) {
            if (permission.get(1).equals(userId)) {
                res.add(NewPermissionFactory.create(
                        permission.get(2),
                        permission.get(1),
                        permission.get(3),
                        permission.get(4)
                        )
                );
            }
        }
        return res;
    }

    @Override
    public void deletePermissionByProjectId(String projectId) throws Exception {
        if (projectId.equals("test dao failure")) {
            throw new Exception("failed successfully");
        }
        for (int i = 0; i < permissions.size(); i++) {
            if (permissions.get(i).get(2).equals(projectId)) {
                permissions.remove(i);
                i--;
            }
        }
    }
}

