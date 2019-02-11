package net.homenet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountServiceImplMockTests {
    private static final String TEST_ACCOUNT_NO = "1234";

    private AccountDao accountDao;
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountDao = mock(AccountDao.class);
        accountService = new AccountServiceImpl(accountDao);
    }

    @Test
    public void deposit() {
        //# Setup
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

        //# Execute
        accountService.deposit(TEST_ACCOUNT_NO, 50);

        //# Verify
        verify(accountDao, times(1)).findAccount(any(String.class));
        verify(accountDao, times(1)).updateAccount(account);

        //# Assert
        assertEquals(150, accountService.getBalance(TEST_ACCOUNT_NO), 0);
    }

    @Test
    public void withdraw() {
        //# Setup
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

        //# Execute
        accountService.withdraw(TEST_ACCOUNT_NO, 50);

        //# Verify
        verify(accountDao, times(1)).findAccount(any(String.class));
        verify(accountDao, times(1)).updateAccount(account);

        //# Assert
        assertEquals(50, accountService.getBalance(TEST_ACCOUNT_NO), 0);
    }

    @Test(expected = InsufficientBalanceException.class)
    public void withdrawWithInsufficientBalance() {
        //# Setup
        Account account = new Account(TEST_ACCOUNT_NO, 100);
        when(accountDao.findAccount(TEST_ACCOUNT_NO)).thenReturn(account);

        //# Execute
        accountService.withdraw(TEST_ACCOUNT_NO, 150);
    }
}
