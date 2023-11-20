package domains.task.use_case.add_task;

import java.time.LocalDateTime;

public class AddTaskInputData {
    private String taskName;
    private String content;
    private LocalDateTime deadline;
    private String projectID;

    public AddTaskInputData(String taskName, String content, LocalDateTime deadline, String projectID) {
        this.taskName = taskName;
        this.content = content;
        this.deadline = deadline;
        this.projectID = projectID;
    }

    String getTaskName() {
        return taskName;
    }

    String getContent() {
        return content;
    }

    LocalDateTime getDeadline() {
        return deadline;
    }

    String getProjectID() {
        return projectID;
    }
}
