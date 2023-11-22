package data_access;

import entity.Account;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {
    private final File csvUserFile;
    private final File csvProjectFile;

    private final List<String> userHeaders = new ArrayList<String>();
    private final Map<String, Account> userMap = new HashMap<>();

    public FileUserDataAccessObject(String csvUserPath, String csvProjectPath) throws FileNotFoundException {
        this.csvUserFile = new File(csvUserPath);
        this.csvProjectFile = new File(csvProjectPath);
        userHeaders.add("account_name");
        userHeaders.add("account_id");
        userHeaders.add("password");
        userHeaders.add("level");

        if (csvUserFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvUserFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("account_name,account_id,password,level");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String accountName = String.valueOf(col[userHeaders.indexOf("account_name")]);
                    String accountId = String.valueOf(col[userHeaders.indexOf("account_id")]);
                    String password = String.valueOf(col[userHeaders.indexOf("password")]);
                    int level = Integer.valueOf(col[userHeaders.indexOf("level")]);
                    Account currentAccount = new Account(accountId, accountName, password);
                    currentAccount.setLevel(level);
                    userMap.put(accountName, currentAccount);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void save(Account newAccount) {
        userMap.put(newAccount.getAccountName(), newAccount);
        this.save();
    }

    public Account get(String accountName) {
        return userMap.get(accountName);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvUserFile));
            writer.write(String.join(",", userHeaders));
            writer.newLine();

            for (Account account : userMap.values()) {
                String line = String.format("%s,%s,%s,%s", account.getAccountName(),
                        account.getAccountID(), account.getPassword(), account.getLevel());
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
