package domains.project.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewProjectFactoryTest {

    @Test
    void create() {
        Project project = NewProjectFactory.create("1", "proj1");
        assertNotNull(project);
        assertEquals(project.getProjectID(), "1");
        assertEquals(project.getProjectName(), "proj1");
        assertInstanceOf(Project.class, project);
    }
}