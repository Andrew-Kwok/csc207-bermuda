package data_access.cloudsql;

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

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlDataAccessObject implements
        SqlDataAccessInterface,
        GetPermissionDataAccessInterface, CreatePermissionDataAccessInterface,
        UpdatePermissionDataAccessInterface, DeletePermissionDataAccessInterface,
        SignupUserDataAccessInterface, LoginUserDataAccessInterface,
        CreateProjectSqlDataAccessInterface, GetProjectSqlDataAccessInterface,
        DeleteProjectSqlDataAccessInterface, ShareProjectDataAccessInterface,
        ShareProjectPageDataAccessInterface {

    private final DataSource sqlDataSource;

    public SqlDataAccessObject(DataSource sqlDataSource) {
        this.sqlDataSource = sqlDataSource;
    }

    public List<Permission> getPermissions(String userID) throws Exception {
        String sql = "SELECT * FROM permission";
        if (userID != null) {
            sql += " WHERE user_id = ?";
        }

        List<Permission> permissions = new ArrayList<>();

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            if (userID != null) {
                statement.setString(1, userID);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String userId = resultSet.getString("user_id");
                    String projectId = resultSet.getString("project_id");
                    String permissionName = resultSet.getString("permission_name");
                    String permissionDescription = resultSet.getString("permission_description");

//                    Permission permission = new Permission(id, projectId, userId, permissionName, permissionDescription);
                    Permission permission = Permission.builder().
                            permissionID(id).
                            projectID(projectId).
                            userID(userId).
                            permissionName(permissionName).
                            permissionDescription(permissionDescription).
                            build();
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

    public void deletePermissionByProjectId(String projectId) throws Exception {
        String sql = "DELETE FROM permission WHERE project_id = ?";

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, projectId);

            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }
    }

    public void clearAllPermissions() throws Exception {
        String sql = "DELETE FROM permission";

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }
    }

    public User getUserByUserId(String userId) throws Exception {
        String sql = "SELECT * FROM user WHERE id = ?";

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return User.builder().
                            userID(resultSet.getString("id")).
                            username(resultSet.getString("username")).
                            password(resultSet.getString("password")).
                            build();

//                    return new User(
//                            resultSet.getString("id"),
//                            resultSet.getString("username"),
//                            resultSet.getString("password"),
                }
            } catch (Exception e) {
                throw new Exception("Error getting user");
            }
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }

        throw new Exception("User not found");
    }

    public User getUserByUsername(String username) throws Exception {
        String sql = "SELECT * FROM user WHERE username = ?";

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return User.builder().
                            userID(resultSet.getString("id")).
                            username(resultSet.getString("username")).
                            password(resultSet.getString("password")).
                            build();

//                    return new User(
//                            resultSet.getString("id"),
//                            resultSet.getString("username"),
//                            resultSet.getString("password"),
                }
            } catch (Exception e) {
                throw new Exception("Error getting user");
            }
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }

        throw new Exception("User not found");
    }

    public void createUser(User user) throws Exception {
        String sql = "INSERT INTO user (id, username, password) VALUES (?, ?, ?)";

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, user.getUserID());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }
    }

    public void updateUser(User user) throws Exception {
        String sql = "UPDATE user SET username = ?, password = ? WHERE id = ?";

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(4, user.getUserID());

            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }
    }

    public void deleteUser(String userID) throws Exception {
        String sql = "DELETE FROM user WHERE id = ?";

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, userID);

            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }
    }

    public void clearAllUsers() throws Exception {
        String sql = "DELETE FROM user";

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }
    }

    public List<List<String>> getUsersNameAndId(String projectId) throws Exception {
        String sql = "SELECT id, username FROM user WHERE id NOT IN (SELECT user_id FROM permission WHERE project_id = ?)";
        List<List<String>> usersNameAndId = new ArrayList<>();

        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, projectId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    List<String> userNameAndId = new ArrayList<>();
                    userNameAndId.add(resultSet.getString("username"));
                    userNameAndId.add(resultSet.getString("id"));
                    usersNameAndId.add(userNameAndId);
                }
            } catch (Exception e) {
                throw new Exception("Error getting user");
            }
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }

        return usersNameAndId;
    }
    public boolean existsByName(String username) throws Exception {
        String sql = "SELECT * FROM user WHERE username = ?";
        try (Connection connection = sqlDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                throw new Exception("Error getting user");
            }
        } catch (Exception e) {
            throw new Exception("Error setting sql connection");
        }
    }
}
