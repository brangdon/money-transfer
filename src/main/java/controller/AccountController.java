package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Account;
import model.ResponseError;
import services.AccountService;
import spark.Route;

import java.util.List;

public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

        public Route createAccount() {
        return (request, response) -> {
            Gson gson = new GsonBuilder().serializeNulls().create();
            Account account = gson.fromJson(request.body(), Account.class);
            accountService.addAccount(account);
            response.status(200);
            return account;

        };
    }

    public Route getAccounts() {
        return (request, response) -> {
            List<Account> accounts = accountService.getAccounts();
            response.status(200);
            return accounts;

        };
    }

    public Route getAccountById() {
        return (request, response) -> {
            String id = request.params(":id");
            int accountId = Integer.parseInt(id);

            Account account = accountService.getAccount(accountId);
            if (account != null) {
                response.status(200);
                return account;
            } else {
                response.status(404);
                response.type("application/json");
                return new ResponseError("No user with id %s found", id);
            }
        };
    }

    public Route getTransfersByAccountId() {
        return (request, response) -> {

            String id = request.params(":id");
            int accountId = Integer.parseInt(id);

            Account account = accountService.getAccount(accountId);
            if (account != null) {
                response.status(200);
                return accountService.getAccountTransfers(accountId);
            } else {
                response.status(404);
                response.type("application/json");
                return new ResponseError("No transfers with user id %s found", id);
            }
        };
    }


}
