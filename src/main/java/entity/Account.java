package entity;

public class Account {

    // Refer to the API documentation for the meaning of these fields.
    private final String accountID;
    private final String accountName;

    public String getAccountID() {
        return accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public Account(String accountID, String accountName) {
        this.accountID = accountID;
        this.accountName = accountName;
    }

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    public static class AccountBuilder {
        private String accountID;
        private String accountName;

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


        public Account build() {
            return new Account(accountID, accountName);
        }
    }

    @Override
    public String toString() {
        return "[%s] Account name: %s\n".formatted(accountID, accountName);
    }
}
