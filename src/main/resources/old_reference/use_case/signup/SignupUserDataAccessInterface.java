package use_case.signup;

import entity.Account;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Account account);
}
