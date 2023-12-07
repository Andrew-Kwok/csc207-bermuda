package domains.user.entity;

import utils.uuid;

public class NewUserFactory {
    public static User create(String name, String password) {
        String userID = uuid.newUUID();
        return new User(userID, name, password);
    }
}
