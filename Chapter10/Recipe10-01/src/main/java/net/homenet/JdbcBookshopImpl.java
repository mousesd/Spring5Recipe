package net.homenet;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings({ "SqlDialectInspection", "WeakerAccess" })
public class JdbcBookshopImpl implements Bookshop {
    private final DataSource dataSource;

    public JdbcBookshopImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void purchase(String isbn, String username) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            //# 1.
            PreparedStatement ps1 = conn.prepareStatement("SELECT price FROM book WHERE isbn = ?");
            ps1.setString(1, isbn);
            ResultSet rs = ps1.executeQuery();

            rs.next();
            int price = rs.getInt("price");
            ps1.close();

            //# 2.
            PreparedStatement ps2 = conn.prepareStatement("UPDATE book_stock SET stock = stock - 1 WHERE isbn = ?");
            ps2.setString(1, isbn);
            ps2.executeUpdate();
            ps2.close();

            //# 3.
            PreparedStatement ps3 = conn.prepareStatement("UPDATE account SET balance = balance - ? WHERE username = ?");
            ps3.setInt(1, price);
            ps3.setString(2, username);

            //# 아래 예외가 발생
            //# org.postgresql.util.PSQLException: 오류: 새 자료가 "account" 릴레이션의 "positive_balance" 체크 제약 조건을 위반했습니다
            ps3.executeUpdate();
            ps3.close();

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
