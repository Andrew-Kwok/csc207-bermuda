package use_case.signup;

import entity.Account;
import entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(Account account);
}
