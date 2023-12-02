package domains.user.entity;

public class User {
    private final String userID;
    private final String username;
    private String password;
    private int userLevel = 1;

    public User(String userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public User(String userID, String username, String password, int userLevel) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.userLevel = userLevel;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public static userBuilder builder() {
        return new userBuilder();
    }

    public static class userBuilder {
        private String userID;
        private String username;
        private String password;
        private int userLevel = 1;
        userBuilder() {
        }

        public userBuilder userID(String userID) {
            this.userID = userID;
            return this;
        }

        public userBuilder username(String username) {
            this.username = username;
            return this;
        }

        public userBuilder password(String password) {
            this.password = password;
            return this;
        }

        public userBuilder userLevel(int userLevel) {
            this.userLevel = userLevel;
            return this;
        }

        public User build() {
            return new User(userID, username, password, userLevel);
        }
    }
}
