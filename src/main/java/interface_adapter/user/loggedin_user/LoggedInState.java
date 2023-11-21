package interface_adapter.user.loggedin_user;

import domains.user.entity.User;

public class LoggedInState {
    private User user;
    private boolean loggedIn = false;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public LoggedInState(LoggedInState copy) {
        user = copy.user;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
