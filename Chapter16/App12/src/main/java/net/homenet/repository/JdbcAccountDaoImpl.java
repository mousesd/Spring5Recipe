package net.homenet.repository;

import net.homenet.domain.Account;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@SuppressWarnings("SqlResolve")
public class JdbcAccountDaoImpl extends JdbcDaoSupport implements AccountDao {
    @Override
    public void createAccount(Account account) {
        assert getJdbcTemplate() != null;
        getJdbcTemplate().update("INSERT INTO ACCOUNT (ACCOUNT_NO, BALANCE) VALUES (?, ?)"
            , account.getAccountNo()
            , account.getBalance());
    }

    @Override
    public void updateAccount(Account account) {
        assert getJdbcTemplate() != null;
        getJdbcTemplate().update("UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_NO = ?"
            , account.getBalance()
            , account.getAccountNo());
    }

    @Override
    public void removeAccount(Account account) {
        assert getJdbcTemplate() != null;
        getJdbcTemplate().update("DELETE FROM ACCOUNT WHERE ACCOUNT_NO = ?", account.getAccountNo());
    }

    @Override
    public Account findAccount(String accountNo) {
        assert getJdbcTemplate() != null;
        Double balance = getJdbcTemplate().queryForObject("SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NO = ?"
            , Double.class
            , accountNo);

        assert balance != null;
        return new Account(accountNo, balance);
    }
}
