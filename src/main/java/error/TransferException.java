package error;

public class TransferException  {
    private int transferId;
    private String message;

    public TransferException(int transferId) {
        this.transferId = transferId;
    }

    public TransferException(String message) {
        this.message = message;
    }

    public TransferException(int accountId, String message) {
        this.transferId = accountId;
        this.message = message;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message + ", ID= " + transferId;
    }
}
