package interface_adapter.task.add_task;

import java.time.LocalDateTime;

public class AddTaskState {

    private String taskName = "";
    private String description = "";
    private LocalDateTime deadline = null;

    public AddTaskState(){}

    public String getTaskName(){
        return taskName;
    }

    public String getDescription(){
        return description;
    }

    public LocalDateTime getDeadline(){
        return deadline;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setDeadline(LocalDateTime deadline){
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "AddTaskState{" +
                "taskName='" + taskName + "\'" +
                "description='" + description + "\'" +
                "deadline='" + deadline.toString() + "\'" +
                "}";
    }
}