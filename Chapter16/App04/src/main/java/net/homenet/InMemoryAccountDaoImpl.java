package net.homenet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "unused", "WeakerAccess" })
public class InMemoryAccountDaoImpl implements AccountDao {
    private Map<String, Account> accounts;

    public InMemoryAccountDaoImpl() {
        accounts = Collections.synchronizedMap(new HashMap<>());
    }

    @Override
    public void createAccount(Account account) {
        if (accountExists(account.getAccountNo())) {
            throw new DuplicateAccountException();
        }
        accounts.put(account.getAccountNo(), account);
    }

    @Override
    public void updateAccount(Account account) {
        if (!accountExists(account.getAccountNo())) {
            throw new AccountNotFoundException();
        }
        accounts.put(account.getAccountNo(), account);
    }

    @Override
    public void removeAccount(Account account) {
        if (!accountExists(account.getAccountNo())) {
            throw new AccountNotFoundException();
        }
        accounts.remove(account.getAccountNo());
    }

    @Override
    public Account findAccount(String accountNo) {
        Account account = accounts.get(accountNo);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

    public boolean accountExists(String accountNo) {
        return accounts.containsKey(accountNo);
    }
}
