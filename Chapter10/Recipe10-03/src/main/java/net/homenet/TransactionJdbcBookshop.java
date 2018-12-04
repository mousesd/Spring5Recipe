package net.homenet;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@SuppressWarnings({ "SqlDialectInspection", "WeakerAccess" })
public class TransactionJdbcBookshop extends JdbcDaoSupport implements Bookshop {
    private final PlatformTransactionManager transactionManager;

    public TransactionJdbcBookshop(DataSource dataSource, PlatformTransactionManager transactionManager) {
        this.setDataSource(dataSource);
        this.transactionManager = transactionManager;
    }

    @Override
    public void purchase(String isbn, String username) {
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);

        try {
            int price = getJdbcTemplate()
                .queryForObject("SELECT price FROM book WHERE isbn = ?", Integer.class, isbn);

            getJdbcTemplate().update("UPDATE book_stock SET stock = stock - 1 WHERE isbn = ?", isbn);

            getJdbcTemplate().update("UPDATE account SET balance = balance - ? WHERE username = ?", price, username);
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            e.printStackTrace();
        }
    }
}
