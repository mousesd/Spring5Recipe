package net.homenet.service;

import net.homenet.configuration.BankConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@SuppressWarnings({ "SqlResolve", "ConstantConditions" })
@ContextConfiguration(classes = BankConfiguration.class)
@Sql(scripts = { "classpath:/ddl.sql" })
public class AccountServiceTestNGSpringContextTests extends AbstractTransactionalTestNGSpringContextTests {

    private static final String TEST_ACCOUNT_NO = "1234";
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @BeforeMethod
    public void setUp() {
        jdbcTemplate.update("INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE) VALUES (?, ?)", TEST_ACCOUNT_NO, 100);
    }

    @Test
    public void testDeposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        Double balance = jdbcTemplate.queryForObject("SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?"
            , Double.class
            , TEST_ACCOUNT_NO);
        assertEquals(balance, 150, 0);
    }

    @Test
    public void testWithdraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        Double balance = jdbcTemplate.queryForObject("SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?"
            , Double.class
            , TEST_ACCOUNT_NO);
        assertEquals(balance, 50, 0);
    }
}
