package domains.task.use_case.add_task;
import java.time.LocalDateTime;

public class AddTaskInputData {
    private String taskName;
    private String description;
    private LocalDateTime deadline;

    public AddTaskInputData(String taskName, String description, LocalDateTime deadline){
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
    }

    String getTaskName(){return taskName;}
    String getDescription(){return description;}
    LocalDateTime getDeadline(){return deadline;}
}
