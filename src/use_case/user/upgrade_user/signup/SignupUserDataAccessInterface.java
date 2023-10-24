package use_case.user.upgrade_user.signup;

import entity.user.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
