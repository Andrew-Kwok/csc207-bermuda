package data_access.todoist;

import config.Config;
import domains.project.entity.Project;
import domains.project.use_case.create_project.CreateProjectApiDataAccessInterface;
import domains.project.use_case.get_project.GetProjectApiDataAccessInterface;
import domains.task.entity.Task;
import domains.task.use_case.add_task.AddTaskDataAccessInterface;
import domains.task.use_case.get_task.GetTaskDataAccessInterface;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiDataAccessObject implements
        CreateProjectApiDataAccessInterface, GetProjectApiDataAccessInterface,
        AddTaskDataAccessInterface, GetTaskDataAccessInterface {

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
    public List<Project> getProjects() throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/projects", Config.getEnv("TODOIST_API_URL")))
                .addHeader("Authorization", String.format("Bearer %s", Config.getEnv("TODOIST_API_TOKEN")))
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                JSONArray responseBody = new JSONArray(response.body().string());

                List<Project> projects = new ArrayList<>();
                for (int i = 0; i < responseBody.length(); i++) {
                    JSONObject project = responseBody.getJSONObject(i);
                    projects.add(new Project(
                            project.getString("id"),
                            project.getString("name")
                    ));
                }
                return projects;
            } else {
                throw new Exception("error getting projects: " + response.code());
            }
        } catch (Exception e) {
            throw new Exception("error connectıng to todoıst: " + e.getMessage());
        }
    }

    @Override
    public void addTask(Task task) throws Exception {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new FormBody.Builder()
                .add("project_id", task.getProjectID())
                .add("name", task.getTaskName())
                .add("content", task.getTaskContent())
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

    @Override
    public ArrayList<Task> getTasks(String projectID) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s/tasks", Config.getEnv("TODOIST_API_URL")))
                .addHeader("Authorization", String.format("Bearer %s", Config.getEnv("TODOIST_API_TOKEN")))
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                JSONArray responseBody = new JSONArray(response.body().string());

                ArrayList<Task> tasks = new ArrayList<>();
                for (int i = 0; i < responseBody.length(); i++) {
                    JSONObject task = responseBody.getJSONObject(i);
                    tasks.add(new Task(
                            task.getString("id"),
                            task.getString("project_id"),
                            task.getString("name"),
                            task.getString("content")
                    ));
                }
                return tasks;
            } else {
                throw new Exception("error getting tasks: " + response.code());
            }
        } catch (Exception e) {
            throw new Exception("error connectıng to todoıst: " + e.getMessage());
        }
    }
}
