package entity;

public class Task {
    private final String taskID;
    private final String accountID;
    private String content;
    private String description;
    private String dueDate;

    public String getTaskID() {
        return taskID;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public Task(String taskID, String accountID, String content, String description, String dueDate) {
        this.taskID = taskID;
        this.accountID = accountID;
        this.content = content;
        this.description = description;
        this.dueDate = dueDate;
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public static class TaskBuilder {
        private String taskID;
        private String accountID;
        private String content;
        private String description;
        private String dueDate;

        TaskBuilder() {
        }

        public TaskBuilder taskID(String taskID) {
            this.taskID = taskID;
            return this;
        }

        public TaskBuilder accountID(String accountID) {
            this.accountID = accountID;
            return this;
        }

        public TaskBuilder content(String content) {
            this.content = content;
            return this;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }
        public TaskBuilder dueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }


        public Task build() {
            return new Task(taskID, accountID, content, description, dueDate);
        }
    }

    @Override
    public String toString() {
        return "[%s|%s] %s, due by %s\n".formatted(accountID, taskID, content, dueDate);
    }
}
