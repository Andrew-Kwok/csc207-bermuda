package data_access;

import entity.Account;

import entity.Task;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static constant.APIConstant.*;

public class ApiDataAccessObject implements ApiDataAccessInterface {
    public static String getApiToken() {
        return "Bearer " + API_TOKEN;
    }

    @Override
    public Account getAccount(String accountID) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.todoist.com/rest/v2/projects/%s", accountID))
                .addHeader("Authorization", getApiToken())
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                JSONObject responseBody = new JSONObject(response.body().string());
                return Account.builder()
                        .accountID(responseBody.getString("id"))
                        .accountName(responseBody.getString("name"))
                        .build();
            } else {
                throw new RuntimeException("error");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Account createAccount(String accountName) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new FormBody.Builder()
                .add("name", accountName)
                .build();
        Request request = new Request.Builder()
                .url(URL_ACCOUNTS)
                .addHeader("Authorization", getApiToken())
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                JSONObject responseBody = new JSONObject(response.body().string());
                return Account.builder()
                        .accountID(responseBody.getString("id"))
                        .accountName(responseBody.getString("name"))
                        .build();
            } else {
                throw new RuntimeException("error");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteAccount(String accountID) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("https://api.todoist.com/rest/v2/projects/%s", accountID))
                .addHeader("Authorization", getApiToken())
                .addHeader("Content-Type", "application/json")
                .delete()
                .build();
        try{
            Response response = client.newCall(request).execute();
            return response.code() == 204;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> getTasks(String accountID) {
        return null;
    }

    @Override
    public Task createTask(String accountID, String content, String description, String dueDate) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        RequestBody body = new FormBody.Builder()
                .add("content", content)
                .add("project_id", accountID)
                .add("due_string", description)
                .add("due_date", dueDate)
                .build();
        Request request = new Request.Builder()
                .url(URL_TASKS)
                .addHeader("Authorization", getApiToken())
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                JSONObject responseBody = new JSONObject(response.body().string());
                return Task.builder()
                        .taskID(responseBody.getString("id"))
                        .accountID(responseBody.getString("project_id"))
                        .content(responseBody.getString("content"))
                        .dueDate(responseBody.getJSONObject("due").getString("date"))
                        .description(responseBody.getJSONObject("due").getString("string"))
                        .build();
            } else {
                throw new RuntimeException("error");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


}
