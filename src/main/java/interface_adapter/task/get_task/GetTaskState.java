package interface_adapter.task.get_task;

import domains.task.entity.Task;

import java.util.ArrayList;

public class GetTaskState {
    private ArrayList<Task> tasks;
    private String getTaskError = null;

    public GetTaskState(){}

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public String getGetTaskError(){
        return getTaskError;
    }

    public void setTasks(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public void setGetTaskError(String getTaskError){
        this.getTaskError = getTaskError;
    }
}
