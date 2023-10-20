package data_access;

import entity.User;
import entity.UserFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {
    private final File csvUserFile;
    private final File csvProjectFile;

    private final Map<String, Integer> userHeaders = new TreeMap<>();
    private final Map<String, User> userMap = new HashMap<>();
    private final Map<String, Integer> projectHeaders = new HashMap<>();
    private final Map<String, User> projectMap = new HashMap<>();
    private UserFactory newUserFactory;

    public FileUserDataAccessObject(String csvUserPath, String csvProjectPath,
                                    UserFactory newUserFactory) throws FileNotFoundException {
        this.csvUserFile = new File(csvUserPath);
        this.csvProjectFile = new File(csvProjectPath);
        this.newUserFactory = newUserFactory;

        userHeaders.put("username", 0);
        userHeaders.put("password", 1);
        userHeaders.put("level", 2);

        if (csvUserFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvUserFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,level");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[userHeaders.get("username")]);
                    String password = String.valueOf(col[userHeaders.get("password")]);
                    int level = Integer.parseInt(col[userHeaders.get("level")]);
                    User user = newUserFactory.create(username, password);
                    user.setUserLevel(level);
                    userMap.put(username, user);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void save(User user) {
        userMap.put(user.getUsername(), user);
        this.save();
    }

    public User get(String username) {
        return userMap.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvUserFile));
            writer.write(String.join(",", userHeaders.keySet()));
            writer.newLine();

            for (User user : userMap.values()) {
                String line = String.format("%s,%s,%s", user.getUsername(), user.getPassword(), user.getUserLevel());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return false;
    }


}
