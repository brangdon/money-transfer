package validator;

import model.Transfer;

public class TransferValidator {


    public TransferValidator() {
    }

    public boolean validateTransfer(Transfer transfer) {

        if (transfer.getAmount().signum() < 0.0) {
            return false;
        }

        if (transfer.getIdAccountFrom() == transfer.getIdAccountTo()) {
            return false;
        }

        return true;
    }


}
