package server;

import controller.AccountController;
import controller.TransferController;
import handler.ErrorsHandler;
import json.JsonUtil;
import services.AccountService;
import services.TransferService;
//import services.PersonService;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

public class MainServer {

    private DataLoader dataLoader;
    private AccountController accountController;
    private TransferController transferController;
    private final JsonUtil json = new JsonUtil();
    private final ErrorsHandler errorsHandler = new ErrorsHandler();

    public MainServer() {
        dataLoader = new DataLoader();
        accountController = new AccountController(new AccountService());
        transferController = new TransferController(new TransferService());
    }

    public void run() {
        prepareAccountRoutes();
        prepareTransferRoutes();
        registerErrorsHandler();
    }

    private void prepareAccountRoutes() {
        get("/account/all", accountController.getAccounts(), json);
        get("/account/:id", accountController.getAccountById(), json);
        get("/account/transfer/:id", accountController.getTransfersByAccountId(), json);
        post("/account/add", accountController.createAccount());
    }

    private void prepareTransferRoutes() {
        get("/transfer/all", transferController.getTransfers(), json);
        get("/transfer/:id", transferController.getTransferById(), json);
        post("/transfer", transferController.transferBetweenAccounts());
    }

    private void registerErrorsHandler() {
        exception(Exception.class, errorsHandler.exceptionsHandler());
    }

}
