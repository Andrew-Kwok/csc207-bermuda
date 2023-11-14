package data_access.fileuser;

import domains.user.entity.NewUserFactory;
import domains.user.entity.User;
import domains.user.use_case.login.LoginUserDataAccessInterface;
import domains.user.use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {
    private final File csvUserFile;
    private final File csvProjectFile;

    private final List<String> userHeaders = new ArrayList<String>();
    private final Map<String, User> userMap = new HashMap<>();
    private final Map<String, Integer> projectHeaders = new HashMap<>();
    private final Map<String, User> projectMap = new HashMap<>();

    public FileUserDataAccessObject(String csvUserPath, String csvProjectPath) throws FileNotFoundException {
        this.csvUserFile = new File(csvUserPath);
        this.csvProjectFile = new File(csvProjectPath);

        userHeaders.add("username");
        userHeaders.add("password");
        userHeaders.add("level");
        userHeaders.indexOf("username");

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
                    String username = String.valueOf(col[userHeaders.indexOf("username")]);
                    String password = String.valueOf(col[userHeaders.indexOf("password")]);
                    int level = Integer.parseInt(col[userHeaders.indexOf("level")]);
                    User user = NewUserFactory.create(username, password);
                    user.setUserLevel(level);
                    userMap.put(username, user);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void createUser(User user) {
        userMap.put(user.getUsername(), user);
        this.save();
    }

    public User getUserByUsername(String username) {
        return userMap.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvUserFile));
            writer.write(String.join(",", userHeaders));
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
        return userMap.containsKey(identifier);
    }


}
