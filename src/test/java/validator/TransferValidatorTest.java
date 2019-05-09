package validator;

import model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TransferValidatorTest {
    TransferValidator transferValidator;

    @Before
    public void init() {
        transferValidator = new TransferValidator();
    }

    @Test
    public void validateTransfersTest() {
        Transfer transfer1 = new Transfer(1, 2, new BigDecimal(101));
        Transfer transfer2 = new Transfer(1, 1, new BigDecimal(102));
        Transfer transfer3 = new Transfer(1, 2, new BigDecimal(-103));

        Assert.assertTrue(transferValidator.validateTransfer(transfer1));
        Assert.assertFalse(transferValidator.validateTransfer(transfer2));
        Assert.assertFalse(transferValidator.validateTransfer(transfer3));

    }
}
