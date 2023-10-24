package entity.user;

import entity.user.User;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(String name, String password);
}
