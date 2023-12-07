package domains.user.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class UserTest {

    private final String userID = "00000000-0000-0000-0000-000000000000";
    private final String username = "user1";
    private final String password = "password";
    private final User user = User.builder().
            userID(userID).
            username(username).
            password(password).
            build();

    @Test
    public void getUserID() {
        assertEquals(userID, user.getUserID());
    }

    @Test
    public void getUsername() {
        assertEquals(username, user.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals(password, user.getPassword());
    }

    @Test
    public void setPassword() {
        String newPassword = "newPassword";
        user.setPassword(newPassword);
        assertEquals(newPassword, user.getPassword());
    }

    @Test
    public void builder() {
        User.userBuilder builder = User.builder();
        assertNotNull(builder);
        assertInstanceOf(User.userBuilder.class, builder);
    }
}