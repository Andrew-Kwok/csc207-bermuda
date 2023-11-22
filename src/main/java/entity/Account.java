package entity;

public class Account {

    // Refer to the API documentation for the meaning of these fields.
    private final String accountID;
    private final String accountName;
    private final String password;
    private int level = 1;

    public int getLevel() {
        return level;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getPassword() {
        return password;
    }

    public Account(String accountID, String accountName, String password) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.password = password;
    }

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    public static class AccountBuilder {
        private String accountID;
        private String accountName;
        private String password;

        AccountBuilder() {
        }

        public AccountBuilder accountID(String acountID) {
            this.accountID = acountID;
            return this;
        }

        public AccountBuilder accountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public AccountBuilder password(String password) {
            this.password = password;
            return this;
        }


        public Account build() {
            return new Account(accountID, accountName, password);
        }
    }

    @Override
    public String toString() {
        return "[%s] Account name: %s\n".formatted(accountID, accountName);
    }
}
