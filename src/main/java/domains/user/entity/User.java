package domains.user.entity;

public class User {
    private final String userID;
    private final String username;
    private String password;
    
    public User(String userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public static userBuilder builder() {
        return new userBuilder();
    }

    public static class userBuilder {
        private String userID;
        private String username;
        private String password;
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

        public User build() {
            return new User(userID, username, password);
        }
    }
}
