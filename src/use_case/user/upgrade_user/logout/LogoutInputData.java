package use_case.user.upgrade_user.logout;

public class LogoutInputData {
    private final String username;

    public LogoutInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
