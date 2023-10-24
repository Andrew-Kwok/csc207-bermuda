package entity.task;

import java.time.ZonedDateTime;

public class Task {
    private String taskID;
    private String todoistTaskID;
    private String taskName;
    private String taskStatus;
    private String taskDescription;
    private ZonedDateTime taskDeadline;

    public Task(String taskID, String todoistTaskID, String taskName, String taskStatus, String taskDescription, ZonedDateTime taskDeadline) {
        this.taskID = taskID;
        this.todoistTaskID = todoistTaskID;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getTodoistTaskID() {
        return todoistTaskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public ZonedDateTime getTaskDeadline() {
        return taskDeadline;
    }

    @Override
public String toString() {
        return ("""
                [Task ID: %s]
                \t Todoist Task ID: %s
                \t Task Name: %s
                \t Task Status: %s
                \t Task Description: %s
                \t Task Deadline: %s
                """).
                formatted(taskID, todoistTaskID, taskName, taskStatus, taskDescription, taskDeadline);
    }
}
