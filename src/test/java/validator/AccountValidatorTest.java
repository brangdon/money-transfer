package validator;

import model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class AccountValidatorTest {

    AccountValidator accountValidator;

    @Before
    public void init() {
        accountValidator = new AccountValidator();
    }

    @Test
    public void validateNewUsersTest() {

        Account account1 = new Account(new BigDecimal(100), "email1");
        Account account2 = new Account(new BigDecimal(100), "");
        Account account3 = new Account(new BigDecimal(-100), "email1");
        Account account4 = new Account(new BigDecimal(-100), "");

        Assert.assertTrue(accountValidator.validateNewAccount(account1));
        Assert.assertFalse(accountValidator.validateNewAccount(account2));
        Assert.assertFalse(accountValidator.validateNewAccount(account3));
        Assert.assertFalse(accountValidator.validateNewAccount(account4));


    }
}
