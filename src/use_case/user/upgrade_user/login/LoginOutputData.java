package use_case.user.upgrade_user.login;

public class LoginOutputData {

    private final String username;
    private final int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public LoginOutputData(String username, int errorCode) {
        this.username = username;
        this.errorCode = errorCode;
    }

    public String getUsername() {
        return username;
    }

}
