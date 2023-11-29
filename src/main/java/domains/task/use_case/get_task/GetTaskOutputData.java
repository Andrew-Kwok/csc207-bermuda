package domains.task.use_case.get_task;

import java.util.List;
import java.util.Map;

public class GetTaskOutputData {
    private final List<Map<String, String>> tasks;
    public GetTaskOutputData(List<Map<String, String>> tasks){
        this.tasks = tasks;
    }

    public List<Map<String, String>> getTasks(){
        return tasks;
    }
}
