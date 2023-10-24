package data_access;

import entity.project.Project;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static constant.APIConstant.API_TOKEN;
import static constant.APIConstant.URL_GET_PROJECT;

public class ApiDataAccessObject implements ApiDataAccessInterface {
    public static String getApiToken() {
        return "Bearer " + API_TOKEN;
    }

    @Override
    public Project getProject(String projectID) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.todoist.com/rest/v2/projects/%s", projectID))
                .addHeader("Authorization", getApiToken())
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                JSONObject responseBody = new JSONObject(response.body().string());
                return Project.builder()
                        .projectID(responseBody.getString("id"))
                        .projectName(responseBody.getString("name"))
                        .build();
            } else {
                throw new RuntimeException("error");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project createProject(String projectName) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new FormBody.Builder()
                .add("name", projectName)
                .build();
        Request request = new Request.Builder()
                .url(URL_GET_PROJECT)
                .addHeader("Authorization", getApiToken())
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                JSONObject responseBody = new JSONObject(response.body().string());
                return Project.builder()
                        .projectID(responseBody.getString("id"))
                        .projectName(responseBody.getString("name"))
                        .build();
            } else {
                throw new RuntimeException("error");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


}
