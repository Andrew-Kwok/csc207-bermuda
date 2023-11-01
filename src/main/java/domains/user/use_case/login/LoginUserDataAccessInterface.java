package domains.user.use_case.login;

import domains.user.entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);

    User get(String username);
}
