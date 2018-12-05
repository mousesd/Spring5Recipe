package net.homenet.homenet;

public interface Bookshop {
    void purchase(String isbn, String username);
    void increaseStock(String isbn, int stock);
    int checkStock(String isbn);
}
