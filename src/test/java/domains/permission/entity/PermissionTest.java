package domains.permission.entity;

import domains.permission.entity.Permission;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PermissionTest {
    private final String permissionID = "00000000-0000-0000-0000-000000000000";
    private final String projectID = "0000000000";
    private final String userID = "00000000-0000-0000-0000-000000000000";
    private final String permissionName = "permissionName";
    private final String permissionDescription = "permissionDescription";

    private final Permission permission = Permission.builder().
            permissionID(permissionID).
            projectID(projectID).
            userID(userID).
            permissionName(permissionName).
            permissionDescription(permissionDescription).
            build();

    @Test
    public void getPermissionID() {
        assertEquals(permissionID, permission.getPermissionID());
    }

    @Test
    public void getProjectID() {
        assertEquals(projectID, permission.getProjectID());
    }

    @Test
    public void getUserID() {
        assertEquals(userID, permission.getUserID());
    }

    @Test
    public void getPermissionName() {
        assertEquals(permissionName, permission.getPermissionName());
    }

    @Test
    public void getPermissionDescription() {
        assertEquals(permissionDescription, permission.getPermissionDescription());
    }

    @Test
    public void setPermissionName() {
        String newPermissionName = "newPermissionName";
        permission.setPermissionName(newPermissionName);
        assertEquals(newPermissionName, permission.getPermissionName());
    }

    @Test
    public void setPermissionDescription() {
        String newPermissionDescription = "newPermissionDescription";
        permission.setPermissionDescription(newPermissionDescription);
        assertEquals(newPermissionDescription, permission.getPermissionDescription());
    }

    @Test
    public void builder() {
        Permission permission = Permission.builder().
                permissionID(permissionID).
                projectID(projectID).
                userID(userID).
                permissionName(permissionName).
                permissionDescription(permissionDescription).
                build();
        assertEquals(permissionID, permission.getPermissionID());
        assertEquals(projectID, permission.getProjectID());
        assertEquals(userID, permission.getUserID());
        assertEquals(permissionName, permission.getPermissionName());
        assertEquals(permissionDescription, permission.getPermissionDescription());
    }

    @Test
    public void testToString() {
        assertNotNull(permission.toString());
        assertEquals(permission.toString(), """
                [Permission ID: 00000000-0000-0000-0000-000000000000]
                \t Project ID: 0000000000
                \t User ID: 00000000-0000-0000-0000-000000000000
                \t Permission Name: permissionName
                \t Permission Description: permissionDescription
                """);
    }

    @Test
    public void toMap() {
        assertNotNull(permission.toMap());
        assertEquals(permission.toMap().size(), 5);
        assertTrue(permission.toMap().containsKey("permissionID"));
        assertTrue(permission.toMap().containsKey("projectID"));
        assertTrue(permission.toMap().containsKey("userID"));
        assertTrue(permission.toMap().containsKey("permissionName"));
        assertTrue(permission.toMap().containsKey("permissionDescription"));
        assertEquals(permission.toMap().get("permissionID"), permissionID);
        assertEquals(permission.toMap().get("projectID"), projectID);
        assertEquals(permission.toMap().get("userID"), userID);
        assertEquals(permission.toMap().get("permissionName"), permissionName);
        assertEquals(permission.toMap().get("permissionDescription"), permissionDescription);
    }
}