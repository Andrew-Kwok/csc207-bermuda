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
        sqlDAO = new SqlDataAccessObject(
                SqlConfig.NewTestSQL()
        );

        // add 1 user
        try{
            sqlDAO.createUser(testUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

        createPermissionInteractor = new CreatePermissionInteractor(createPermissionPresenter, sqlDAO);

        CreatePermissionInputData createPermissionInputData = new CreatePermissionInputData(
                testUser.getUserID(),
                "0123456789",
                "permission-name",
                "permission-description"
        );

        createPermissionInteractor.execute(createPermissionInputData);
    }

    @Test
    public void testCreatePermissionWithNullInput() {
        createPermissionPresenter = new CreatePermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(CreatePermissionOutputData createPermissionOutputData) {
                System.out.println("CreatePermissionInteractorTest.TestCreatePermissionPresenter.prepareSuccessView");
                System.out.println(createPermissionOutputData.getPermissionId());
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Input is null", error);
            }
        };

        createPermissionInteractor = new CreatePermissionInteractor(createPermissionPresenter, sqlDAO);

        createPermissionInteractor.execute(null);
    }

    @Test
    public void testCreatePermissionWithNullUserID() {
        createPermissionPresenter = new CreatePermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(CreatePermissionOutputData createPermissionOutputData) {
                System.out.println("CreatePermissionInteractorTest.TestCreatePermissionPresenter.prepareSuccessView");
                System.out.println(createPermissionOutputData.getPermissionId());
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User ID is null or empty", error);
            }
        };

        createPermissionInteractor = new CreatePermissionInteractor(createPermissionPresenter, sqlDAO);

        CreatePermissionInputData createPermissionInputData = new CreatePermissionInputData(
                null,
                "0123456789",
                "permission-name",
                "permission-description"
        );

        createPermissionInteractor.execute(createPermissionInputData);
    }

    @Test
    public void testCreatePermissionWithEmptyUserID() {
        createPermissionPresenter = new CreatePermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(CreatePermissionOutputData createPermissionOutputData) {
                System.out.println("CreatePermissionInteractorTest.TestCreatePermissionPresenter.prepareSuccessView");
                System.out.println(createPermissionOutputData.getPermissionId());
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User ID is null or empty", error);
            }
        };

        createPermissionInteractor = new CreatePermissionInteractor(createPermissionPresenter, sqlDAO);

        CreatePermissionInputData createPermissionInputData = new CreatePermissionInputData(
                "",
                "0123456789",
                "permission-name",
                "permission-description"
        );

        createPermissionInteractor.execute(createPermissionInputData);
    }

    @Test
    public void testCreatePermissionWithNullProjectID() {
        createPermissionPresenter = new CreatePermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(CreatePermissionOutputData createPermissionOutputData) {
                System.out.println("CreatePermissionInteractorTest.TestCreatePermissionPresenter.prepareSuccessView");
                System.out.println(createPermissionOutputData.getPermissionId());
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Project ID is null or empty", error);
            }
        };

        createPermissionInteractor = new CreatePermissionInteractor(createPermissionPresenter, sqlDAO);

        CreatePermissionInputData createPermissionInputData = new CreatePermissionInputData(
                testUser.getUserID(),
                null,
                "permission-name",
                "permission-description"
        );

        createPermissionInteractor.execute(createPermissionInputData);
    }

    @Test
    public void testCreatePermissionWithEmptyProjectID() {
        createPermissionPresenter = new CreatePermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(CreatePermissionOutputData createPermissionOutputData) {
                System.out.println("CreatePermissionInteractorTest.TestCreatePermissionPresenter.prepareSuccessView");
                System.out.println(createPermissionOutputData.getPermissionId());
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Project ID is null or empty", error);
            }
        };

        createPermissionInteractor = new CreatePermissionInteractor(createPermissionPresenter, sqlDAO);

        CreatePermissionInputData createPermissionInputData = new CreatePermissionInputData(
                testUser.getUserID(),
                "",
                "permission-name",
                "permission-description"
        );

        createPermissionInteractor.execute(createPermissionInputData);
    }

    @Test
    public void testCreatePermissionWithNullPermissionName() {
        createPermissionPresenter = new CreatePermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(CreatePermissionOutputData createPermissionOutputData) {
                System.out.println("CreatePermissionInteractorTest.TestCreatePermissionPresenter.prepareSuccessView");
                System.out.println(createPermissionOutputData.getPermissionId());
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Permission name is null or empty", error);
            }
        };

        createPermissionInteractor = new CreatePermissionInteractor(createPermissionPresenter, sqlDAO);

        CreatePermissionInputData createPermissionInputData = new CreatePermissionInputData(
                testUser.getUserID(),
                "0123456789",
                null,
                "permission-description"
        );

        createPermissionInteractor.execute(createPermissionInputData);
    }

    @Test
    public void testCreatePermissionWithNullDAO() {
        createPermissionPresenter = new CreatePermissionOutputBoundary() {
            @Override
            public void prepareSuccessView(CreatePermissionOutputData createPermissionOutputData) {
                System.out.println("CreatePermissionInteractorTest.TestCreatePermissionPresenter.prepareSuccessView");
                System.out.println(createPermissionOutputData.getPermissionId());
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Cannot invoke \"domains.permission.use_case.create_permission.CreatePermissionDataAccessInterface.createPermission(domains.permission.entity.Permission)\" because \"this.dataAccess\" is null", error);
            }
        };

        createPermissionInteractor = new CreatePermissionInteractor(createPermissionPresenter, null);

        CreatePermissionInputData createPermissionInputData = new CreatePermissionInputData(
                testUser.getUserID(),
                "0123456789",
                "permission-name",
                "permission-description"
        );

        createPermissionInteractor.execute(createPermissionInputData);
    }
}