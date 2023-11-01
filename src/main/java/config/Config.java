package config;

import app.Bermuda;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties envVariables;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        envVariables = new Properties();
        try {
            InputStream input = Bermuda.class.getClassLoader().getResourceAsStream("config/.env");

            if (input != null) {
                envVariables.load(input);
            } else {
                System.err.println("The .env file was not found.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getEnv(String key) {
        String value = envVariables.getProperty(key);
        if (value == null) {
            System.err.println("Environment variable not found: " + key);
        }
        return value;
    }

}
