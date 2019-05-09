package validator;

import model.Account;
import services.AccountService;

public class AccountValidator {

//    private AccountService accountService;

//    public AccountValidator(AccountService accountService) {
//        this.accountService = accountService;
//    }

    public boolean validateNewAccount(Account account) {
        if (account.getBalance().signum() < 0.0) {
            return false;
        } else if (account.getEmail() == "" || account.getEmail() == null) {
            return false;
        }
        return true;
    }

//    public boolean validateAccount(int accountId) {
//        Account account = accountService.getAccount(accountId);
//        if (account != null) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
