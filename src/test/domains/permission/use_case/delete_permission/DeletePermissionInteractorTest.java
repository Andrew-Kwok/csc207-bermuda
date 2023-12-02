package domains.permission.use_case.delete_permission;

import data_access.cloudsql.SqlConfig;
import data_access.cloudsql.SqlDataAccessObject;
import domains.permission.entity.Permission;
import domains.permission.use_case.create_permission.CreatePermissionInteractor;
import domains.permission.use_case.get_permission.GetPermissionInputData;
import domains.permission.use_case.get_permission.GetPermissionInteractor;
import domains.permission.use_case.get_permission.GetPermissionOutputBoundary;
import domains.permission.use_case.get_permission.GetPermissionOutputData;
import domains.user.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static constant.ViewConstant.*;
import static constant.ViewConstant.PERMISSION_DESCRIPTION;
import static org.junit.Assert.*;

public class DeletePermissionInteractorTest {
    private DeletePermissionOutputBoundary deletePermissionPresenter;
    private SqlDataAccessObject sqlDAO;
    private DeletePermissionInteractor deletePermissionInteractor;
    private final List<User> testUsers = new ArrayList<>(
            List.of(
                    new User(
                            "00000000-0000-0000-0000-000000000000",
                            "test-user-1",
                            "test-password"
                    )
            )
    );
    private List<Permission> testPermissions = new ArrayList<>(
            List.of(
                    new Permission(
                            "10000000-0000-0000-0000-000000000000",
                            "0123456789",
                            "00000000-0000-0000-0000-000000000000",
                            "permission-1 user-1",
                            "permission-description"
                    ),
                    new Permission(
                            "20000000-0000-0000-0000-000000000000",
                            "0123456789",
                            "00000000-0000-0000-0000-000000000000",
                            "permission-2 user-1",
                            "permission-description"
                    )
            )
    );

    @Before
    public void setUp() throws Exception {
        sqlDAO = new SqlDataAccessObject(
                SqlConfig.NewTestSQL()
        );

        // add 1 user
        try{
            for (User user : testUsers) {
                sqlDAO.createUser(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // add 2 permissions
        try {
            for (Permission permission : testPermissions) {
                sqlDAO.createPermission(permission);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void tearDown() throws Exception {
        try {
            sqlDAO.clearAllPermissions();
            sqlDAO.clearAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testDeletePermissionInteractor() {
        deletePermissionPresenter = new DeletePermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(DeletePermissionOutputData deletePermissionOutputData) {
                List<Permission> permissions;
                try {
                    permissions = sqlDAO.getPermissions(null);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                assertEquals(1, permissions.size());
                for (int i = 0; i < 1; ++i) {
                    assertEquals(testPermissions.get(i).getPermissionID(), permissions.get(i).getPermissionID());
                    assertEquals(testPermissions.get(i).getProjectID(), permissions.get(i).getProjectID());
                    assertEquals(testPermissions.get(i).getUserID(), permissions.get(i).getUserID());
                    assertEquals(testPermissions.get(i).getPermissionName(), permissions.get(i).getPermissionName());
                    assertEquals(testPermissions.get(i).getPermissionDescription(), permissions.get(i).getPermissionDescription());
                }
            }

            @Override
            public void prepareFailView(String error) {
                System.out.println("DeletePermissionInteractorTest.TestDeletePermissionPresenter.prepareFailView");
                System.out.println(error);
                fail();
            }
        };

        testPermissions.remove(0);

        deletePermissionInteractor = new DeletePermissionInteractor(deletePermissionPresenter, sqlDAO);
        deletePermissionInteractor.execute(new DeletePermissionInputData(
                "10000000-0000-0000-0000-000000000000"
        ));
    }
}