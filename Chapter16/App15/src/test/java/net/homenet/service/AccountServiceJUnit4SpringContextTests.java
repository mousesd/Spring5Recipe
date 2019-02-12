package net.homenet.service;

import net.homenet.configuration.BankConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.Assert.*;

@SuppressWarnings("SqlDialectInspection")
@ContextConfiguration(classes = BankConfiguration.class)
@Sql(scripts = { "classpath:/ddl.sql" })
public class AccountServiceJUnit4SpringContextTests extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String TEST_ACCOUNT_NO = "1234";
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Before
    public void setUp() {
        jdbcTemplate.update("INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE) VALUES (?, ?)", TEST_ACCOUNT_NO, 100);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    @Timed(millis = 1000)
    public void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        Double balance = jdbcTemplate.queryForObject("SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?"
            , Double.class
            , TEST_ACCOUNT_NO);
        assertEquals(balance, 150.0, 0);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    @Repeat(5)
    public void withdraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        Double balance = jdbcTemplate.queryForObject("SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?"
            , Double.class
            , TEST_ACCOUNT_NO);
        assertEquals(balance, 50.0, 0);
    }
}
