package domains.task.entity;

import domains.project.entity.NewProjectFactory;
import domains.project.entity.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

public class NewTaskFactoryTest {
    @Test
    void createNoDesc() {
        Task task = NewTaskFactory.create("0", "0", "task0");
        Assertions.assertNotNull(task);
        Assertions.assertEquals(task.getTaskID(), "0");
        Assertions.assertEquals(task.getProjectID(), "0");
        Assertions.assertEquals(task.getTaskName(), "task0");
        assertInstanceOf(Task.class, task);
    }

    @Test
    void create(){
        Task task = NewTaskFactory.create("0", "0", "task0", "desc0");
        Assertions.assertNotNull(task);
        Assertions.assertEquals(task.getTaskID(), "0");
        Assertions.assertEquals(task.getProjectID(), "0");
        Assertions.assertEquals(task.getTaskName(), "task0");
        Assertions.assertEquals(task.getTaskDescription(), "desc0");
        assertInstanceOf(Task.class, task);
    }
}