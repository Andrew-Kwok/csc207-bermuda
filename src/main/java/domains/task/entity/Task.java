package domains.task.entity;

import java.util.Map;

import static constant.ViewConstant.*;

public class Task {
    private final String taskID;
    private final String projectID;
    private String taskName;
    private String taskDescription = "";
    public Task(String taskID, String projectID, String taskName) {
        this.taskID = taskID;
        this.projectID = projectID;
        this.taskName = taskName;
    }

    public Task(String taskID, String projectID, String taskName, String taskDescription) {
        this.taskID = taskID;
        this.projectID = projectID;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public static taskBuilder builder() {
        return new taskBuilder();
    }

    public static class taskBuilder {
        private String taskID;
        private String projectID;
        private String taskName;

        private String taskDescription = "";

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

        public taskBuilder taskDescription(String taskDescription) {
            this.taskDescription = taskDescription;
            return this;
        }

        public Task build() {
            return new Task(taskID, projectID, taskName, taskDescription);
        }
    }

    public String getTaskID() {
        return taskID;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return ("""
                [Task ID: %s]
                \t Project ID: %s,
                \t Task Name: %s,
                \t Task Description: %s
                """).
                formatted(taskID, projectID, taskName, taskDescription);
    }

    public Map<String, String> toMap() {
        return Map.of(
                TASK_ID, taskID,
                PROJECT_ID, projectID,
                TASK_NAME, taskName,
                TASK_DESCRIPTION, taskDescription
        );
    }
}
