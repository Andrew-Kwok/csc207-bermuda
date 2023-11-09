package domains.task.use_case.add_task;

import domains.task.entity.Task;

public class AddTaskOutputData {
    private final Task task;
    public AddTaskOutputData(Task task){this.task = task;}
    public Task getTask(){return task;}
    public String getTaskID(){return task.getTaskID();}
}
