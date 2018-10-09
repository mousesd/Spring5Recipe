package net.homenet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan("net.homenet")
@Profile("global")
public class ShopConfigurationGlobal {
    @Bean(initMethod = "openFile", destroyMethod = "closeFile")
    public Cashier cashier() {
        Cashier cashier = new Cashier();
        cashier.setFileName("checkout");
        cashier.setPath("/Users/mousesd/Documents");
        return cashier;
    }
}
