package domains.task.use_case.add_task;

import domains.task.entity.Task;
import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.close_task.CloseTaskDataAccessInterface;
import domains.task.use_case.edit_task.EditTaskDataAccessInterface;
import domains.task.use_case.get_task.GetTaskDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryDAO implements AddTaskDataAccessInterface, GetTaskDataAccessInterface, EditTaskDataAccessInterface, CloseTaskDataAccessInterface {
    private HashMap<String, Task> tasks =  new HashMap<>();
    private static int counter = 0;
    private static int idLength = 10;
    @Override
    public void addTask(Task task) throws Exception {
        String taskID;
        String counterStr = Integer.toString(counter);
        int numDigits = counterStr.length();

        taskID = "0".repeat(idLength - numDigits) + counterStr;

        tasks.put(taskID, task);
    }

    @Override
    public void closeTask(String taskID) throws Exception {
        tasks.remove(taskID);
    }

    @Override
    public void editTask(Task task) throws Exception {

    }

    @Override
    public List<Task> getTasks(String projectID) throws Exception {
        List<Task> list = new ArrayList<Task>(tasks.values());

        return list;
    }
}
