package net.homenet.repository;

import net.homenet.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDaoImpl implements UserDao {
    private final DataSource dataSource;

    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User registerUser(User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer id = jdbcTemplate.queryForObject("INSERT INTO USER_REGISTRATION (FIRST_NAME, LAST_NAME, COMPANY, ADDRESS, CITY, STATE, ZIP"
                + ", COUNTY, URL, PHONE_NUMBER, FAX) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING ID"
            , new Object[] { user.getFirstName()
                , user.getLastName()
                , user.getCompany()
                , user.getAddress()
                , user.getCity()
                , user.getState()
                , user.getZip()
                , user.getCounty()
                , user.getUrl()
                , user.getPhoneNumber()
                , user.getFax() }
            , Integer.class);

        user.setId(id);
        return user;
    }
}
