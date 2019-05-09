package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.ResponseError;
import model.Transfer;
import services.TransferService;
import spark.Route;
import validator.TransferValidator;

import java.util.List;

public class TransferController {

    private TransferService transferService;
    private TransferValidator transferValidator;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
        this.transferValidator = new TransferValidator();
    }

    public Route getTransfers() {
        return (request, response) -> {
            List<Transfer> transfers = transferService.getTransfers();
            return transfers;
        };
    }

    public Route getTransferById() {
        return (request, response) -> {
            String id = request.params(":id");
            int transferId = Integer.parseInt(id);

            Transfer transfer = transferService.getTransferById(transferId);
            if (transfer != null) {
                response.status(200);
                return transfer;
            } else {
                response.status(404);
                response.type("application/json");
                return new ResponseError("No transfer with id %s found", id);
            }

        };
    }

    public Route transferBetweenAccounts() {
        return (request, response) -> {
            Gson gson = new GsonBuilder().serializeNulls().create();
            Transfer transfer = gson.fromJson(request.body(), Transfer.class);

            if (transferValidator.validateTransfer(transfer)) {
                response.status(200);
                return transferService.moneyTransfer(transfer);
            } else {
                response.status(404);
                response.type("application/json");
                return new ResponseError("Bad transfer");
            }
        };
    }
}
