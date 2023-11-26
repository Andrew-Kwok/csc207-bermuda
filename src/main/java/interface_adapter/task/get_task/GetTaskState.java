package interface_adapter.task.get_task;

import domains.task.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class GetTaskState {
    private String projectID = null;
    private List<Task> tasks = new ArrayList<>();
    private String getTaskError = null;
    private boolean initial = false;

    public GetTaskState(GetTaskState copy) {
        this.projectID = copy.projectID;
        this.tasks = copy.tasks;
        this.getTaskError = copy.getTaskError;
        this.initial = copy.initial;
    }

    public GetTaskState() {
    }

    public String getProjectID(){
        return projectID;
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public String getGetTaskError(){
        return getTaskError;
    }

    public boolean isInitial(){
        return initial;
    }

    public void setProjectID(String projectID){
        this.projectID = projectID;
    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
    }

    public void setGetTaskError(String getTaskError){
        this.getTaskError = getTaskError;
    }

    public void setInitial(boolean initial){
        this.initial = initial;
    }
}
