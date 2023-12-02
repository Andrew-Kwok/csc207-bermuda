package interface_adapter.task.get_task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetTaskState {
    private String projectID = null;
    private List<Map<String, String>> tasks = new ArrayList<>();
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

    public List<Map<String, String>> getTasks(){
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

    public void setTasks(List<Map<String, String>> tasks){
        this.tasks = tasks;
    }

    public void setGetTaskError(String getTaskError){
        this.getTaskError = getTaskError;
    }

    public void setInitial(boolean initial){
        this.initial = initial;
    }
}
