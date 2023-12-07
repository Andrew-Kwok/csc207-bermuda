package domains.user.use_case.signup;

import data_access.cloudsql.SqlConfig;
import data_access.cloudsql.SqlDataAccessObject;
import domains.user.entity.NewUserFactory;
import domains.user.use_case.signup.SignupInputData;
import domains.user.use_case.signup.SignupInteractor;
import domains.user.use_case.signup.SignupOutputBoundary;
import domains.user.use_case.signup.SignupOutputData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignupInteractorTest {
    private SqlDataAccessObject sqlDAO;

    @Before
    public void setUp() {
        sqlDAO = new SqlDataAccessObject(
                SqlConfig.NewTestSQL()
        );
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
    public void testSignup() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {
                assertEquals("test-user", signupOutputData.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail();
            }
        };

        SignupInteractor signupInteractor = new SignupInteractor(sqlDAO, signupPresenter);
        signupInteractor.execute(new SignupInputData("test-user", "test-password", "test-password"));
    }

    @Test
    public void testSignupWithDifferentPasswords() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };

        SignupInteractor signupInteractor = new SignupInteractor(sqlDAO, signupPresenter);
        signupInteractor.execute(new SignupInputData("test-user", "test-password", "test-password-different"));
    }

    @Test
    public void testSignupWithExistingUsername() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }
        };

        SignupInteractor signupInteractor = new SignupInteractor(sqlDAO, signupPresenter);

        try {
            sqlDAO.createUser(NewUserFactory.create("test-user", "test-password"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }

        signupInteractor.execute(new SignupInputData("test-user", "test-password", "test-password"));
    }

    @Test
    public void testSignupWithEmptyUsername() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("username cannot be blank", error);
            }
        };

        SignupInteractor signupInteractor = new SignupInteractor(sqlDAO, signupPresenter);
        signupInteractor.execute(new SignupInputData("", "test-password", "test-password"));
    }

    @Test
    public void testSignupWithEmptyPassword() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("you need a password", error);
            }
        };

        SignupInteractor signupInteractor = new SignupInteractor(sqlDAO, signupPresenter);
        signupInteractor.execute(new SignupInputData("test-user", "", ""));
    }

    @Test
    public void testSignupWithNullDAO() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {
                fail();
            }

            @Override
            public void prepareFailView(String error) {
                if (error.equals("Cannot invoke \"domains.user.use_case.signup.SignupUserDataAccessInterface.existsByName(String)\" because \"this.userDataAccessObject\" is null")) {
                    return;
                } else if (error.equals("Cannot invoke \"domains.user.use_case.signup.SignupUserDataAccessInterface.createUser(domains.user.entity.User)\" because \"this.userDataAccessObject\" is null")) {
                    return;
                } else {
                    fail();
                }
            }
        };

        SignupInteractor signupInteractor = new SignupInteractor(null, signupPresenter);
        signupInteractor.execute(new SignupInputData("test-user", "test-password", "test-password"));
    }
}