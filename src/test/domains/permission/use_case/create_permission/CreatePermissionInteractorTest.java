package domains.permission.use_case.create_permission;

import data_access.cloudsql.SqlConfig;
import data_access.cloudsql.SqlDataAccessObject;
import domains.permission.entity.Permission;
import domains.user.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CreatePermissionInteractorTest {
    private CreatePermissionOutputBoundary createPermissionPresenter;
    private SqlDataAccessObject sqlDAO;
    private CreatePermissionInteractor createPermissionInteractor;
    private User testUser = new User(
            "00000000-0000-0000-0000-000000000000",
            "test-user",
            "test-password"
    );

    @Before
    public void setUp() {
        createPermissionPresenter = new CreatePermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(CreatePermissionOutputData createPermissionOutputData) {
                List<Permission> permissions = null;
                try {
                    permissions = sqlDAO.getPermissions(testUser.getUserID());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                assertEquals(1, permissions.size());
                assertEquals(createPermissionOutputData.getPermissionId(), permissions.get(0).getPermissionID());
                assertEquals("0123456789", permissions.get(0).getProjectID());
                assertEquals("permission-name", permissions.get(0).getPermissionName());
                assertEquals("permission-description", permissions.get(0).getPermissionDescription());
            }

            @Override
            public void prepareFailView(String error) {
                System.out.println("CreatePermissionInteractorTest.TestCreatePermissionPresenter.prepareFailView");
                System.out.println(error);
                fail();
            }
        };

        sqlDAO = new SqlDataAccessObject(
                SqlConfig.NewTestSQL()
        );

        // add 1 user
        try{
            sqlDAO.createUser(testUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        createPermissionInteractor = new CreatePermissionInteractor(createPermissionPresenter, sqlDAO);
    }

    @After
    public void tearDown() {
        try {
            sqlDAO.clearAllPermissions();
            sqlDAO.clearAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreatePermission() {
        CreatePermissionInputData createPermissionInputData = new CreatePermissionInputData(
                testUser.getUserID(),
                "0123456789",
                "permission-name",
                "permission-description"
        );

        createPermissionInteractor.execute(createPermissionInputData);
    }
}