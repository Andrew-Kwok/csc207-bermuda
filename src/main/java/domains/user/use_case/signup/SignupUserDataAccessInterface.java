package domains.user.use_case.signup;

import domains.user.entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
