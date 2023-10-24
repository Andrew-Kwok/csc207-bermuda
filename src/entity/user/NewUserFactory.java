package entity.user;

import entity.utils.uuid;

public class NewUserFactory implements UserFactory {
    @Override
    public User create(String name, String password) {
        String userID = uuid.newUUID();
        return new User(userID, name, password);
    }
}
