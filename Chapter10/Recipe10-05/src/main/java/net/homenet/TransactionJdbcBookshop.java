package net.homenet;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings({ "SqlDialectInspection", "WeakerAccess", "Duplicates" })
public class TransactionJdbcBookshop extends JdbcDaoSupport implements Bookshop {
    @Override
    @Transactional
    public void purchase(String isbn, String username) {
        assert getJdbcTemplate() != null;
        Integer price = getJdbcTemplate()
            .queryForObject("SELECT price FROM book WHERE isbn = ?", Integer.class, isbn);

        getJdbcTemplate().update("UPDATE book_stock SET stock = stock - 1 WHERE isbn = ?", isbn);

        getJdbcTemplate().update("UPDATE account SET balance = balance - ? WHERE username = ?", price, username);
    }
}
