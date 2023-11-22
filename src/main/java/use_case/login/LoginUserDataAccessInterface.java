package use_case.login;

import entity.Account;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Account newAccount);

    Account get(String accountName);
}