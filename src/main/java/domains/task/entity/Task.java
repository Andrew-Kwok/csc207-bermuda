package domains.task.entity;

import java.time.LocalTime;
import java.time.ZonedDateTime;

public class Task {
    private final String taskID;
    private final String projectID;
    private String taskName;
    private String taskStatus = "IPR";
    private String taskDescription = "";
    private ZonedDateTime taskDeadline = ZonedDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(LocalTime.MAX.getNano());

    public Task(String taskID, String projectID, String taskName) {
        this.taskID = taskID;
        this.projectID = projectID;
        this.taskName = taskName;
    }

    public Task(String taskID, String projectID, String taskName, String taskStatus, String taskDescription, ZonedDateTime taskDeadline) {
        this.taskID = taskID;
        this.projectID = projectID;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
    }

    public static taskBuilder builder() {
        return new taskBuilder();
    }

    public static class taskBuilder {
        private String taskID;
        private String projectID;
        private String taskName;
        private String taskStatus = "IPR";
        private String taskDescription = "";
        private ZonedDateTime taskDeadline = ZonedDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(LocalTime.MAX.getNano());

        taskBuilder() {
        }

        public taskBuilder taskID(String taskID) {
            this.taskID = taskID;
            return this;
        }

        public taskBuilder projectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public taskBuilder taskName(String taskName) {
            this.taskName = taskName;
            return this;
        }

        public taskBuilder taskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
            return this;
        }

        public taskBuilder taskDescription(String taskDescription) {
            this.taskDescription = taskDescription;
            return this;
        }

        public taskBuilder taskDeadline(ZonedDateTime taskDeadline) {
            this.taskDeadline = taskDeadline;
            return this;
        }

        public Task build() {
            return new Task(taskID, projectID, taskName, taskStatus, taskDescription, taskDeadline);
        }
    }

    public String getTaskID() {
        return taskID;
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

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public void setTaskDeadline(ZonedDateTime taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    @Override
public String toString() {
        return ("""
                [Task ID: %s]
                \t Project ID: %s
                \t Task Name: %s
                \t Task Status: %s
                \t Task Description: %s
                \t Task Deadline: %s
                """).
                formatted(taskID, projectID, taskName, taskStatus, taskDescription, taskDeadline);
    }
}
