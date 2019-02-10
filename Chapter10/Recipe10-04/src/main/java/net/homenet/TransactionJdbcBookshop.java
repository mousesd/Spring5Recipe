package net.homenet;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@SuppressWarnings({ "SqlDialectInspection", "WeakerAccess", "Duplicates" })
public class TransactionJdbcBookshop extends JdbcDaoSupport implements Bookshop {
    private final TransactionTemplate transactionTemplate;

    public TransactionJdbcBookshop(DataSource dataSource, TransactionTemplate transactionTemplate) {
        this.setDataSource(dataSource);
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public void purchase(String isbn, String username) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                int price = getJdbcTemplate()
                    .queryForObject("SELECT price FROM book WHERE isbn = ?", Integer.class, isbn);

                getJdbcTemplate().update("UPDATE book_stock SET stock = stock - 1 WHERE isbn = ?", isbn);

                getJdbcTemplate().update("UPDATE account SET balance = balance - ? WHERE username = ?", price, username);
            }
        });
    }
}
