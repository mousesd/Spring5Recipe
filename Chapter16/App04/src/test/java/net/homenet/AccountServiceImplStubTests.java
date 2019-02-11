package net.homenet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountServiceImplStubTests {
    private static final String TEST_ACCOUNT_NO = "1234";

    private AccountDaoStub accountDao;
    private AccountService accountService;

    @Before
    public void setUp() {
        accountDao = new AccountDaoStub();
        accountDao.accountNo = TEST_ACCOUNT_NO;
        accountDao.balance = 100;
        accountService = new AccountServiceImpl(accountDao);
    }

    @Test
    public void deposit() {
        accountService.deposit(TEST_ACCOUNT_NO, 50);
        assertEquals(150, accountDao.balance, 0);
    }

    @Test
    public void withdraw() {
        accountService.withdraw(TEST_ACCOUNT_NO, 50);
        assertEquals(50, accountDao.balance, 0);
    }

    @Test(expected = InsufficientBalanceException.class)
    public void withdrawWithInsufficientBalance() {
        accountService.withdraw(TEST_ACCOUNT_NO, 150);
    }

    private class AccountDaoStub implements AccountDao {
        private String accountNo;
        private double balance;

        @Override
        public void createAccount(Account account) { }

        @Override
        public void updateAccount(Account account) {
            accountNo = account.getAccountNo();
            balance = account.getBalance();
        }

        @Override
        public void removeAccount(Account account) { }

        @Override
        public Account findAccount(String accountNo) {
            return new Account(this.accountNo, this.balance);
        }
    }
}
