package domains.user.entity;

import utils.uuid;

public class NewUserFactory implements UserFactory {
    @Override
    public User create(String name, String password) {
        String userID = uuid.newUUID();
        return new User(userID, name, password);
    }

    public User create(String name, String password, int userLevel) {
        String userID = uuid.newUUID();
        return new User(userID, name, password, userLevel);
    }
}
