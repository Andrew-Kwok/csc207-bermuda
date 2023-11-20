package domains.project.use_case.get_project;

import domains.task.entity.Task;

import java.util.ArrayList;

public class GetProjectOutputData {
    ArrayList<Task> tasks;

    public GetProjectOutputData(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    ArrayList<Task> getTasks(){
        return tasks;
    }
}
