package domains.task.entity;

import constant.ViewConstant;
import domains.project.entity.Project;
import domains.task.entity.Task;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    private final String myTASK_ID_0 = "0";
    private final String getMyTASK_ID_1 = "1";
    private final String myPROJECT_ID = "proj";
    private final String myTASK_NAME = "task";
    private final String myTASK_DESC = "desc";

    private final Task task = Task.builder()
            .taskID(myTASK_ID_0)
            .projectID(myPROJECT_ID)
            .taskName(myTASK_NAME)
            .taskDescription(myTASK_DESC)
            .build();

    private final Task taskNoDesc = new Task(getMyTASK_ID_1, myPROJECT_ID, myTASK_NAME);

    @Test
    void setTaskName() {
        Task copy = Task.builder()
                .taskID(task.getTaskID())
                .projectID(task.getProjectID())
                .taskName(task.getTaskName())
                .build();
        copy.setTaskName("new name");
        assertEquals(copy.getTaskName(), "new name");
        assertNotEquals(copy.getTaskName(), task.getTaskName());
        assertEquals(copy.getTaskID(), task.getTaskID());
    }

    @Test
    void setTaskDesc(){
        Task copy = Task.builder()
                .taskID(task.getTaskID())
                .projectID(task.getProjectID())
                .taskName(task.getTaskName())
                .taskDescription(task.getTaskDescription())
                .build();
        copy.setTaskDescription("new desc");
        assertEquals(copy.getTaskDescription(), "new desc");
        assertNotEquals(copy.getTaskDescription(), task.getTaskDescription());
        assertEquals(copy.getTaskID(), task.getTaskID());
    }

    @Test
    void testToString() {
        assertNotNull(task.toString());
        assertEquals(task.toString(), """
                [Task ID: 0]
                	 Project ID: proj,
                	 Task Name: task,
                	 Task Description: desc
                """);
    }

    @Test
    void toMap() {
        assertNotNull(task.toMap());
        assertEquals(task.toMap().size(), 4);
        assertTrue(task.toMap().containsKey(ViewConstant.TASK_ID));
        assertTrue(task.toMap().containsKey(ViewConstant.PROJECT_ID));
        assertTrue(task.toMap().containsKey(ViewConstant.TASK_NAME));
        assertTrue(task.toMap().containsKey(ViewConstant.TASK_DESCRIPTION));
        assertEquals(task.toMap().get(ViewConstant.TASK_ID), myTASK_ID_0);
        assertEquals(task.toMap().get(ViewConstant.PROJECT_ID), myPROJECT_ID);
        assertEquals(task.toMap().get(ViewConstant.TASK_NAME), myTASK_NAME);
    assertEquals(task.toMap().get(ViewConstant.TASK_DESCRIPTION), myTASK_DESC);
    }

}