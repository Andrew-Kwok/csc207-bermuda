package domains.task.use_case.get_task;

import domains.task.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class GetTaskOutputData {
    private final List<Task> tasks;
    public GetTaskOutputData(List<Task> tasks){
        this.tasks = tasks;
    }

    public List<Task> getTasks(){
        return tasks;
    }
}
