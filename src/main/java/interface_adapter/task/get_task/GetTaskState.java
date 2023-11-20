package interface_adapter.task.get_task;

import com.google.api.services.sqladmin.SQLAdmin;
import domains.task.entity.Task;

import java.util.ArrayList;

public class GetTaskState {
    private String projectID = "";
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private String getTaskError;

    public GetTaskState(){}

    public String getProjectID(){
        return projectID;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public String getGetTaskError(){
        return getTaskError;
    }

    public void setProjectID(String projectID){
        this.projectID = projectID;
    }

    public void setTasks(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public void setGetTaskError(String getTaskError){
        this.getTaskError = getTaskError;
    }

    @Override
    public String toString(){
        return "GetTaskState{" +
                "projectID='" + projectID + "\'" +
                "}";
    }
}
