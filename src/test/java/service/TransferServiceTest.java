package service;

import model.Transfer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.TransferService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransferServiceTest {

    @Mock
    TransferService transferService;

    @Test
    public void getTransfersTest() {
        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer1 = new Transfer(1, 2, new BigDecimal(101));
        Transfer transfer2 = new Transfer(1, 2, new BigDecimal(102));
        Transfer transfer3 = new Transfer(1, 2, new BigDecimal(103));
        Transfer transfer4 = new Transfer(1, 2, new BigDecimal(104));

        transfers.add(transfer1);
        transfers.add(transfer2);
        transfers.add(transfer3);
        transfers.add(transfer4);

        when(transferService.getTransfers()).thenReturn(transfers);

        //test
        List<Transfer> testList = transferService.getTransfers();

        assertEquals(4, testList.size());
        verify(transferService, times(1)).getTransfers();
    }

    @Test
    public void getTransferByIdTest() {
        Transfer transfer = new Transfer(1, 2, new BigDecimal(101));

        when(transferService.getTransferById(0)).thenReturn(transfer);

        //test
        Transfer testTransfer = transferService.getTransferById(0);

        assertEquals(transfer, testTransfer);
        verify(transferService, times(1)).getTransferById(0);
    }

    @Test
    public void makeTransferTest() {
        Transfer transfer = new Transfer(1, 2, new BigDecimal(101));

        transferService.moneyTransfer(transfer);

        verify(transferService, times(1)).moneyTransfer(transfer);
    }


}
