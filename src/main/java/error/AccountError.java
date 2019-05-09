package error;

public class AccountError {
    private int accountId;
    private String message;

    public AccountError(int accountId) {
        this.accountId = accountId;
    }

    public AccountError(String message) {
        this.message = message;
    }

    public AccountError(int accountId, String message) {
        this.accountId = accountId;
        this.message = message;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message + ", ID: " + accountId;
    }
}
