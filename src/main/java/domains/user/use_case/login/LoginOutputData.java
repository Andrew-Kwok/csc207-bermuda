package domains.user.use_case.login;

import domains.user.entity.User;

public class LoginOutputData {

    private final User user;
    private final int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public LoginOutputData(User user, int errorCode) {
        this.user = user;
        this.errorCode = errorCode;
    }

    public User getUser() {
        return user;
    }

}
