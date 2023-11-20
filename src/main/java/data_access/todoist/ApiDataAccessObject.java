package data_access.todoist;

import config.Config;
import domains.project.entity.Project;
import domains.project.use_case.create_project.CreateProjectApiDataAccessInterface;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ApiDataAccessObject implements
        CreateProjectApiDataAccessInterface {

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
        } catch (IOException | JSONException e) {
            throw new Exception("error connectıng to todoıst: " + e.getMessage());
        }
    }
}
