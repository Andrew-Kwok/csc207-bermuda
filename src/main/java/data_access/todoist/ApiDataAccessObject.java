package data_access.todoist;

import config.Config;
import domains.project.entity.Project;
import domains.project.use_case.create_project.CreateProjectApiDataAccessInterface;
import domains.task.entity.Task;
import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import okhttp3.*;
import org.json.JSONObject;

public class ApiDataAccessObject implements
        CreateProjectApiDataAccessInterface,
        AddTaskDataAccessInterface {
    public Project getProject(String projectID) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/projects/%s", Config.getEnv("TODOIST_API_URL"), projectID))
                .addHeader("Authorization", String.format("Bearer %s", Config.getEnv("TODOIST_API_TOKEN")))
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                JSONObject responseBody = new JSONObject(response.body().string());
                return new Project(
                        responseBody.getString("id"),
                        responseBody.getString("name")
                );
            } else {
                throw new Exception("error retrieving projects: " + response.code());
            }
        } catch (Exception e) {
            throw new Exception("error connectıng to todoıst: " + e.getMessage());
        }
    }

    @Override
    public String createProject(String projectName) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new FormBody.Builder()
                .add("name", projectName)
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/projects", Config.getEnv("TODOIST_API_URL")))
                .addHeader("Authorization", String.format("Bearer %s", Config.getEnv("TODOIST_API_TOKEN")))
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                JSONObject responseBody = new JSONObject(response.body().string());
                return responseBody.getString("id");
            } else {
                throw new Exception("error creating projects: " + response.code());
            }
        } catch (Exception e) {
            throw new Exception("error connectıng to todoıst: " + e.getMessage());
        }
    }

    @Override
    public void addTask(String projectId, Task task) throws Exception {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new FormBody.Builder()
                .add("name", task.getTaskName())
                .add("content", task.getTaskDescription())
                .add("project_id", task.getProjectID())
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/tasks", Config.getEnv("TODOIST_API_URL")))
                .addHeader("Authorization", String.format("Bearer %s", Config.getEnv("TODOIST_API_TOKEN")))
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {

            } else {
                throw new Exception("error creating tasks: " + response.code());
            }
        } catch (Exception e) {
            throw new Exception("error connectıng to todoıst: " + e.getMessage());
        }
    }
}
