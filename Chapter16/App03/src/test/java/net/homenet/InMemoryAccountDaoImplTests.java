package net.homenet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InMemoryAccountDaoImplTests {
    private static final String EXISTING_ACCOUNT_NO = "1234";
    private static final String NEW_ACCOUNT_NO = "5678";

    private Account existingAccount;
    private Account newAccount;
    private InMemoryAccountDaoImpl accountDao;

    @Before
    public void setUp() {
        existingAccount = new Account(EXISTING_ACCOUNT_NO, 100);
        newAccount = new Account(NEW_ACCOUNT_NO, 200);

        accountDao = new InMemoryAccountDaoImpl();
        accountDao.createAccount(existingAccount);
    }

    @Test
    public void accountExists() {
        assertTrue(accountDao.accountExists(EXISTING_ACCOUNT_NO));
        assertFalse(accountDao.accountExists(NEW_ACCOUNT_NO));
    }

    @Test
    public void createAccount() {
        accountDao.createAccount(newAccount);
        assertEquals(accountDao.findAccount(NEW_ACCOUNT_NO), newAccount);
    }

    @Test(expected = DuplicateAccountException.class)
    public void createDuplicateAccount() {
        accountDao.createAccount(existingAccount);
    }

    @Test
    public void updateAccount() {
        existingAccount.setBalance(150);
        accountDao.updateAccount(existingAccount);
        assertEquals(accountDao.findAccount(EXISTING_ACCOUNT_NO), existingAccount);
    }

    @Test(expected = AccountNotFoundException.class)
    public void updateNotExistedAccount() {
        accountDao.updateAccount(newAccount);
    }

    @Test
    public void removeAccount() {
        accountDao.removeAccount(existingAccount);
        assertFalse(accountDao.accountExists(EXISTING_ACCOUNT_NO));
    }

    @Test(expected = AccountNotFoundException.class)
    public void removeNotExistedAccount() {
        accountDao.removeAccount(newAccount);
    }

    @Test
    public void findAccount() {
        Account account = accountDao.findAccount(EXISTING_ACCOUNT_NO);
        assertEquals(existingAccount, account);
    }

    @Test(expected = AccountNotFoundException.class)
    public void findNotExistedAccount() {
        accountDao.findAccount(NEW_ACCOUNT_NO);
    }
}
