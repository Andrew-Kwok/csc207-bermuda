package domains.user.use_case.login;

import data_access.cloudsql.SqlConfig;
import data_access.cloudsql.SqlDataAccessObject;
import domains.user.entity.User;
import domains.user.use_case.login.*;
import interface_adapter.user.login.LoginPresenter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginInteractorTest {
    private SqlDataAccessObject sqlDAO;
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

        try {
            sqlDAO.createUser(testUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @After
    public void tearDown() {
        try {
            sqlDAO.clearAllUsers();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void testLogin() {
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals(testUser.getUserID(), user.getUser().getUserID());
                assertEquals(testUser.getUsername(), user.getUser().getUsername());
                assertEquals(testUser.getPassword(), user.getUser().getPassword());
            }

            @Override
            public void prepareFailView(LoginOutputData error) {
                fail();
            }
        };

        LoginInteractor loginInteractor = new LoginInteractor(sqlDAO, loginPresenter);
        loginInteractor.execute(new LoginInputData(
                testUser.getUsername(),
                testUser.getPassword()
        ));
    }

    @Test
    public void testLoginWrongUsername() {
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail();
            }

            @Override
            public void prepareFailView(LoginOutputData error) {
                assertEquals(1, error.getErrorCode());
            }
        };

        LoginInteractor loginInteractor = new LoginInteractor(sqlDAO, loginPresenter);
        loginInteractor.execute(new LoginInputData(
                "wrong-username",
                testUser.getPassword()
        ));
    }

    @Test
    public void testLoginWrongPassword() {
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail();
            }

            @Override
            public void prepareFailView(LoginOutputData error) {
                assertEquals(2, error.getErrorCode());
            }
        };

        LoginInteractor loginInteractor = new LoginInteractor(sqlDAO, loginPresenter);
        loginInteractor.execute(new LoginInputData(
                testUser.getUsername(),
                "wrong-password"
        ));
    }

    @Test
    public void testLoginNullDAO() {
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail();
            }

            @Override
            public void prepareFailView(LoginOutputData error) {
                assertEquals(3, error.getErrorCode());
            }
        };

        LoginInteractor loginInteractor = new LoginInteractor(null, loginPresenter);
        loginInteractor.execute(new LoginInputData(
                testUser.getUsername(),
                testUser.getPassword()
        ));
    }

    @Test
    public void testLoginMissingExistsByName() {
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail();
            }

            @Override
            public void prepareFailView(LoginOutputData error) {
                assertEquals(3, error.getErrorCode());
            }
        };

        LoginUserDataAccessInterface loginUserDataAccessInterface = new LoginUserDataAccessInterface() {
            @Override
            public boolean existsByName(String username) throws Exception {
                throw new Exception("test");
            }

            @Override
            public User getUserByUsername(String username) throws Exception {
                fail();
                return null;
            }

            @Override
            public void createUser(User user) throws Exception {
                fail();
            }
        };

        LoginInteractor loginInteractor = new LoginInteractor(loginUserDataAccessInterface, loginPresenter);
        loginInteractor.execute(new LoginInputData(
                testUser.getUsername(),
                testUser.getPassword()
        ));
    }

    @Test
    public void testLoginMissingGetUserByUsername() {
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                fail();
            }

            @Override
            public void prepareFailView(LoginOutputData error) {
                assertEquals(3, error.getErrorCode());
            }
        };

        LoginUserDataAccessInterface loginUserDataAccessInterface = new LoginUserDataAccessInterface() {
            @Override
            public boolean existsByName(String username) throws Exception {
                return true;
            }

            @Override
            public User getUserByUsername(String username) throws Exception {
                throw new Exception("test");
            }

            @Override
            public void createUser(User user) throws Exception {
                fail();
            }
        };

        LoginInteractor loginInteractor = new LoginInteractor(loginUserDataAccessInterface, loginPresenter);
        loginInteractor.execute(new LoginInputData(
                testUser.getUsername(),
                testUser.getPassword()
        ));
    }
}