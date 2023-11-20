package domains.task.use_case.get_task;

import domains.task.entity.Task;

import java.util.ArrayList;

public class GetTaskOutputData {
    private final ArrayList<Task> tasks;
    public GetTaskOutputData(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }
}
