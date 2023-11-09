package domains.task.use_case.edit_task;

import java.time.LocalDateTime;

public class EditTaskInputData {
    private String taskName;
    private String description;
    private LocalDateTime deadline;

    public EditTaskInputData(String taskName, String description, LocalDateTime deadline){
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
    }

    String getTaskName(){return taskName;}
    String getDescription(){return description;}
    LocalDateTime getDeadline(){return deadline;}
}
