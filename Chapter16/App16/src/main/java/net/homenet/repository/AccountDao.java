package net.homenet.repository;

import net.homenet.domain.Account;

public interface AccountDao {
    void createAccount(Account account);
    void updateAccount(Account account);
    void removeAccount(Account account);
    Account findAccount(String accountNo);
}
