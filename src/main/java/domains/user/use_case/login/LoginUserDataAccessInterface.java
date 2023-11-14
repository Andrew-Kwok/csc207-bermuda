package domains.user.use_case.login;

import domains.user.entity.User;

public interface LoginUserDataAccessInterface {
    boolean existsByName(String identifier) throws Exception;

    void createUser(User user) throws Exception;

    User getUserByUsername(String username) throws Exception;
}
