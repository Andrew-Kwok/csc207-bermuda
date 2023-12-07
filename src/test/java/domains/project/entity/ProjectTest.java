package domains.project.entity;

import org.junit.jupiter.api.Test;
import constant.ViewConstant;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    private final String myPROJECT_ID = "1";
    private final String myPROJECT_NAME = "proj1";

    private final Project project = Project.builder()
            .projectID(myPROJECT_ID)
            .projectName(myPROJECT_NAME)
            .build();

    @Test
    void setProjectName() {
        Project copy = Project.builder()
                .projectID(project.getProjectID())
                .projectName(project.getProjectName())
                .build();
        copy.setProjectName("new name");
        assertEquals(copy.getProjectName(), "new name");
        assertNotEquals(copy.getProjectName(), project.getProjectName());
        assertEquals(copy.getProjectID(), project.getProjectID());
    }

    @Test
    void testToString() {
        assertNotNull(project.toString());
        assertEquals(project.toString(), """
                [Project ID: 1]
                \t Project Name: proj1
                """);
    }

    @Test
    void toMap() {
        assertNotNull(project.toMap());
        assertEquals(project.toMap().size(), 2);
        assertTrue(project.toMap().containsKey(ViewConstant.PROJECT_ID));
        assertTrue(project.toMap().containsKey(ViewConstant.PROJECT_NAME));
        assertEquals(project.toMap().get(ViewConstant.PROJECT_ID), myPROJECT_ID);
        assertEquals(project.toMap().get(ViewConstant.PROJECT_NAME), myPROJECT_NAME);

    }
}