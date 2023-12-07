package domains.permission.use_case.get_permission;

import data_access.cloudsql.SqlConfig;
import data_access.cloudsql.SqlDataAccessObject;
import domains.permission.entity.Permission;
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
import static org.junit.Assert.*;

public class GetPermissionInteractorTest {
    private GetPermissionOutputBoundary getPermissionPresenter;
    private SqlDataAccessObject sqlDAO;
    private GetPermissionInteractor getPermissionInteractor;
    private final List<User> testUsers = new ArrayList<>(
            List.of(
                    new User(
                            "00000000-0000-0000-0000-000000000000",
                            "test-user-1",
                            "test-password"
                    ),
                    new User(
                            "00000000-0000-0000-0000-000000000001",
                            "test-user-2",
                            "test-password"
                    )
            )
    );
    private final List<Permission> testPermissions = new ArrayList<>(
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
                    ),
                    new Permission(
                            "30000000-0000-0000-0000-000000000000",
                            "0123456789",
                            "00000000-0000-0000-0000-000000000001",
                            "permission-3 user-2",
                            "permission-description"
                    )
            )
    );

    @Before
    public void setUp() throws Exception {
        sqlDAO = new SqlDataAccessObject(
                SqlConfig.NewTestSQL()
        );

        // add 2 users
        try{
            for (User user : testUsers) {
                sqlDAO.createUser(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // add 3 permissions
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
    public void testGetPermissionInteractor() {
        getPermissionPresenter = new GetPermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(GetPermissionOutputData getPermissionOutputData) {
                assertEquals(3, getPermissionOutputData.getPermissions().size());
                for (int i = 0; i < 3; ++i) {
                    assertEquals(testPermissions.get(i).getPermissionID(), getPermissionOutputData.getPermissions().get(i).get(PERMISSION_ID));
                    assertEquals(testPermissions.get(i).getProjectID(), getPermissionOutputData.getPermissions().get(i).get(PROJECT_ID));
                    assertEquals(testPermissions.get(i).getUserID(), getPermissionOutputData.getPermissions().get(i).get(USER_ID));
                    assertEquals(testPermissions.get(i).getPermissionName(), getPermissionOutputData.getPermissions().get(i).get(PERMISSION_NAME));
                    assertEquals(testPermissions.get(i).getPermissionDescription(), getPermissionOutputData.getPermissions().get(i).get(PERMISSION_DESCRIPTION));
                }
            }

            @Override
            public void prepareFailView(String error) {
                System.out.println("GetPermissionInteractorTest.TestGetPermissionPresenter.prepareFailView");
                System.out.println(error);
                fail();
            }
        };

        getPermissionInteractor = new GetPermissionInteractor(getPermissionPresenter, sqlDAO);
        getPermissionInteractor.execute(new GetPermissionInputData(null));
    }

    @Test
    public void testGetPermissionInteractorFilterByUserId() {
        getPermissionPresenter = new GetPermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(GetPermissionOutputData getPermissionOutputData) {
                assertEquals(2, getPermissionOutputData.getPermissions().size());
                for (int i = 0; i < 2; ++i) {
                    assertEquals(testPermissions.get(i).getPermissionID(), getPermissionOutputData.getPermissions().get(i).get(PERMISSION_ID));
                    assertEquals(testPermissions.get(i).getProjectID(), getPermissionOutputData.getPermissions().get(i).get(PROJECT_ID));
                    assertEquals(testPermissions.get(i).getUserID(), getPermissionOutputData.getPermissions().get(i).get(USER_ID));
                    assertEquals(testPermissions.get(i).getPermissionName(), getPermissionOutputData.getPermissions().get(i).get(PERMISSION_NAME));
                    assertEquals(testPermissions.get(i).getPermissionDescription(), getPermissionOutputData.getPermissions().get(i).get(PERMISSION_DESCRIPTION));
                }
            }

            @Override
            public void prepareFailView(String error) {
                System.out.println("GetPermissionInteractorTest.TestGetPermissionPresenter.prepareFailView");
                System.out.println(error);
                fail();
            }
        };

        getPermissionInteractor = new GetPermissionInteractor(getPermissionPresenter, sqlDAO);
        getPermissionInteractor.execute(new GetPermissionInputData(testUsers.get(0).getUserID()));
    }

    @Test
    public void testGetPermissionInteractorWithNullInput() {
        getPermissionPresenter = new GetPermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(GetPermissionOutputData getPermissionOutputData) {
                System.out.println("GetPermissionInteractorTest.TestGetPermissionPresenter.prepareSuccessView");
                System.out.println(getPermissionOutputData.getPermissions());
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Cannot invoke \"domains.permission.use_case.get_permission.GetPermissionDataAccessInterface.getPermissions(String)\" because \"this.dataAccess\" is null", error);
            }
        };

        getPermissionInteractor = new GetPermissionInteractor(getPermissionPresenter, null);
        getPermissionInteractor.execute(new GetPermissionInputData(null));
    }
}