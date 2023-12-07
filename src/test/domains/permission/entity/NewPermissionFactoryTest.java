package domains.permission.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class NewPermissionFactoryTest {
    @Test
    public void create() {
        Permission permission = NewPermissionFactory.create("0000000000", "00000000-0000-0000-0000-000000000000\n", "permissionName", "permissionDescription");
        assertNotNull(permission);
        assertNotNull(permission.getPermissionID());
        assertEquals(permission.getProjectID(), "0000000000");
        assertEquals(permission.getUserID(), "00000000-0000-0000-0000-000000000000\n");
        assertEquals(permission.getPermissionName(), "permissionName");
        assertEquals(permission.getPermissionDescription(), "permissionDescription");
        assertInstanceOf(Permission.class, permission);
    }
}