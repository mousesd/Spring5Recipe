package net.homenet;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

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

    @Override
    @Transactional(
        isolation = Isolation.READ_COMMITTED,
        rollbackFor = IOException.class,
        noRollbackFor = RuntimeException.class
    )
    public void increaseStock(String isbn, int stock) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " - Prepare to increase book stock");

        assert getJdbcTemplate() != null;
        getJdbcTemplate().update("UPDATE book_stock SET stock = stock + ? WHERE isbn = ?", stock, isbn);

        System.out.println(threadName + " - Book stock increased by " + stock);
        sleep(threadName);

        System.out.println(threadName + " - Book stock rollback");
        throw new RuntimeException();
    }

    @Override
    //@Transactional(isolation = Isolation.READ_UNCOMMITTED)
    //@Transactional(isolation = Isolation.READ_COMMITTED)
    //@Transactional(isolation = Isolation.REPEATABLE_READ)
    @Transactional
    public int checkStock(String isbn) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " - Prepare to check book stock");

        assert getJdbcTemplate() != null;
        Integer stock = getJdbcTemplate()
            .queryForObject("SELECT stock FROM book_stock WHERE isbn = ?", Integer.class, isbn);

        System.out.println(threadName + " - Book stock(before sleep) is " + stock);
        sleep(threadName);

        stock = getJdbcTemplate()
            .queryForObject("SELECT stock FROM book_stock WHERE isbn = ?", Integer.class, isbn);
        System.out.println(threadName + " - Book stock(after sleep) is " + stock);

        assert stock != null;
        return stock;
    }

    private void sleep(String threadName) {
        System.out.println(threadName + " - Sleeping");
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName + " - Wake up");
    }
}
