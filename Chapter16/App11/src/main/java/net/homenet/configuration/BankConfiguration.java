package net.homenet.configuration;

import net.homenet.repository.AccountDao;
import net.homenet.repository.InMemoryAccountDaoImpl;
import net.homenet.repository.JdbcAccountDaoImpl;
import net.homenet.service.AccountService;
import net.homenet.service.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BankConfiguration {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:h2:mem:bank-testing");
        dataSource.setUsername("sa");
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public AccountDao accountDao(DataSource dataSource) {
        JdbcAccountDaoImpl accountDao = new JdbcAccountDaoImpl();
        accountDao.setDataSource(dataSource);
        return accountDao;
    }

    @Bean
    public AccountService accountService(AccountDao accountDao) {
        return new AccountServiceImpl(accountDao);
    }
}
