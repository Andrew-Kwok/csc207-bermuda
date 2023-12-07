package domains.user.entity;

import domains.user.entity.NewUserFactory;
import domains.user.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class NewUserFactoryTest {
    @Test
    public void create() {
        User user = NewUserFactory.create("user1", "password");
        assertNotNull(user);
        assertNotNull(user.getUserID());
        assertEquals(user.getUsername(), "user1");
        assertEquals(user.getPassword(), "password");
        assertInstanceOf(User.class, user);
    }
}