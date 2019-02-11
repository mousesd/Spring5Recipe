package net.homenet.configuration;

import net.homenet.repository.AccountDao;
import net.homenet.repository.InMemoryAccountDaoImpl;
import net.homenet.service.AccountService;
import net.homenet.service.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankConfiguration {
    @Bean
    public AccountDao accountDao() {
        return new InMemoryAccountDaoImpl();
    }

    @Bean
    public AccountService accountService(AccountDao accountDao) {
        return new AccountServiceImpl(accountDao);
    }
}
