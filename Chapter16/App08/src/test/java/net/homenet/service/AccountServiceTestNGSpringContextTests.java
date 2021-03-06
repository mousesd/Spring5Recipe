package net.homenet.service;

import net.homenet.configuration.BankConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.*;

@ContextConfiguration(classes = BankConfiguration.class)
public class AccountServiceTestNGSpringContextTests extends AbstractTestNGSpringContextTests {
    private static final String TEST_ACCOUNT_NO = "1234";

    private AccountService accountService;

    @BeforeMethod
    public void setUp() {
        assert applicationContext != null;
        accountService = applicationContext.getBean(AccountService.class);
        accountService.createAccount(TEST_ACCOUNT_NO);
        accountService.deposit(TEST_ACCOUNT_NO, 100);
    }

    @AfterMethod
    public void tearDown() {
        accountService.removeAccount(TEST_ACCOUNT_NO);
    }

    @Test
    public void testDeposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 150, 0);
    }

    @Test
    public void testWithdraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        assertEquals(accountService.getBalance(TEST_ACCOUNT_NO), 50, 0);
    }
}
