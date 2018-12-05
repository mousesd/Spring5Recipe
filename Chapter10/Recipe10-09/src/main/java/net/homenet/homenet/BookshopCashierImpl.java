package net.homenet.homenet;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class BookshopCashierImpl implements Cashier {
    private final Bookshop bookshop;

    public BookshopCashierImpl(Bookshop bookshop) {
        this.bookshop = bookshop;
    }

    @Override
    @Transactional
    public void checkout(List<String> isbns, String username) {
        for (String isbn : isbns) {
            bookshop.purchase(isbn, username);
        }
    }
}
