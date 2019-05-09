package service;

import model.Account;
import model.Transfer;
import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.AccountService;
import validator.AccountValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

//    @InjectMocks
//    AccountService accountService;
//    @Mock
//    SessionFactory sessionFactory;
//    @Mock
//    Session session;

    @Mock
    AccountService accountService;

    @Test
    public void getAccountTest() {
        Account account = new Account(new BigDecimal(100), "email@at.at");
        when(accountService.getAccount(0)).thenReturn(account);

        //test
        Account testAccount = accountService.getAccount(0);

        assertEquals(account, testAccount);
        verify(accountService, times(1)).getAccount(0);
    }

    @Test
    public void getAccountsTest() {
        List<Account> list = new ArrayList<>();
        Account account1 = new Account(new BigDecimal(100), "email1@at.at");
        Account account2 = new Account(new BigDecimal(200), "email2@at.at");
        Account account3 = new Account(new BigDecimal(300), "email3@at.at");

        list.add(account1);
        list.add(account2);
        list.add(account3);

        when(accountService.getAccounts()).thenReturn(list);

        //test
        List<Account> testList = accountService.getAccounts();

        assertEquals(3, testList.size());
        verify(accountService, times(1)).getAccounts();
    }

    @Test
    public void getAccountsTransferTest() {

        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer1 = new Transfer(1,2,new BigDecimal(101));
        Transfer transfer2 = new Transfer(1,2,new BigDecimal(102));
        Transfer transfer3 = new Transfer(1,2,new BigDecimal(103));
        Transfer transfer4 = new Transfer(1,2,new BigDecimal(104));

        transfers.add(transfer1);
        transfers.add(transfer2);
        transfers.add(transfer3);
        transfers.add(transfer4);

        when(accountService.getAccountTransfers(0)).thenReturn(transfers);

        //test
        List<Transfer> transfersTest = accountService.getAccountTransfers(0);

        assertEquals(4, transfersTest.size());
        verify(accountService, times(1)).getAccountTransfers(0);
    }

    @Test
    public void createAccountTest(){
        Account account = new Account(new BigDecimal(100), "email1@at.at");

        accountService.addAccount(account);

        verify(accountService, times(1)).addAccount(account);
    }

//    private SessionFactory sessionFactory;
//    private AccountValidator accountValidator;

//    @Before
//    public void initializeDatabase() {
//        sessionFactory = new Configuration().configure()
//                .buildSessionFactory();
//        Session session = sessionFactory.openSession();
////        accountValidator = new AccountValidator();
//
//        session.doWork(new Work() {
//            @Override
//            public void execute(Connection connection) throws SQLException {
//                try {
//                    File script = new File(getClass().getResource("/data.sql").getFile());
//                    RunScript.execute(connection, new FileReader(script));
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException("could not initialize with script");
//                }
//            }
//        });
//        session.close();
//    }

//    @Test
//    public void testValidAccount() {
//        Account account = new Account(new BigDecimal(1000), "email@hotmail.com");
//        Assert.assertTrue(accountValidator.validateNewAccount(account));
//    }
//
//    @Test
//    public void testInvalidAccount() {
//        Account account = new Account(new BigDecimal(-1000), "email@hotmail.com");
//        Assert.assertFalse(accountValidator.validateNewAccount(account));
//    }

//    @Test
//    public void testPersist_success() {
//        Account account = new Account(new BigDecimal(1000), "email@hotmail.com");
//
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.save(account);
//        session.getTransaction().commit();
//
//        session = sessionFactory.openSession();
//        List<Account> books = session.createQuery("FROM Account").list();
//
//        assertNotNull(books);
//        assertEquals(3, books.size());
//        session.close();
//    }


//    @Test
//    public void getAccounts() {
//        Session session = sessionFactory.openSession();
//        List<Account> accounts = session.createQuery("FROM Account").list();
//
//        assertNotNull(accounts);
//        assertEquals(2, accounts.size());
//        session.close();
//    }
}
