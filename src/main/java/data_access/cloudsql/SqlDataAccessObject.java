package data_access.cloudsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import domains.permission.entity.Permission;
import domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface;
import domains.permission.use_case.get_permission.GetPermissionDataAccessInterface;

public class SqlDataAccessObject implements
        SqlDataAccessInterface,
        GetPermissionDataAccessInterface, CreatePermissionDataAccessInterface {

    private final DataSource sqlDataSource;

    public SqlDataAccessObject(DataSource sqlDataSource) {
        this.sqlDataSource = sqlDataSource;
    }

    public List<Permission> getPermissions(String userID) throws Exception {
        String sql = "SELECT * FROM permission WHERE user_id = ?";
        List<Permission> permissions = new ArrayList<>();

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String userId = resultSet.getString("user_id");
                    String projectId = resultSet.getString("project_id");
                    String permissionName = resultSet.getString("permission_name");
                    String permissionDescription = resultSet.getString("permission_description");

                    Permission permission = new Permission(id, userId, projectId, permissionName, permissionDescription);
                    permissions.add(permission);
                }
            } catch (Exception e) {
                throw new Exception("Error getting permission");
            }
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }

        return permissions;
    }

    public void createPermission(Permission permission) throws Exception {
        String sql = "INSERT INTO permission (id, user_id, project_id, permission_name, permission_description) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, permission.getPermissionID());
            statement.setString(2, permission.getUserID());
            statement.setString(3, permission.getProjectID());
            statement.setString(4, permission.getPermissionName());
            statement.setString(5, permission.getPermissionDescription());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }
    }

    public void updatePermission(Permission permission) throws Exception {
        String sql = "UPDATE permission SET permission_name = ?, permission_description = ? WHERE id = ?";

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, permission.getPermissionName());
            statement.setString(2, permission.getPermissionDescription());
            statement.setString(3, permission.getPermissionID());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }
    }

    public void deletePermission(String permissionID) throws Exception {
        String sql = "DELETE FROM permission WHERE id = ?";

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, permissionID);

            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }
    }
}
