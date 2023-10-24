package use_case.add_task;
import java.time.LocalDateTime;

public class CreateTaskInputData {
    private String taskName;
    private String description;
    private LocalDateTime deadline;

    public CreateTaskInputData(String taskName, String description, LocalDateTime deadline){
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
    }

    String getTaskName(){return taskName;}
    String getDescription(){return description;}
    LocalDateTime getDeadline(){return deadline;}
}
