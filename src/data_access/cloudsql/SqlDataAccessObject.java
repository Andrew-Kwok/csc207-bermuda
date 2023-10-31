package data_access.cloudsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import domains.permission.entity.Permission;

public class SqlDataAccessObject implements SqlDataAccessInterface {

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
}
