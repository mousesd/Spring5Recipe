package net.homenet.s1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("net.homenet.s1")
public class ShopConfiguration {
    @Bean
    public Product battery() {
        Battery battery = new Battery("AAA", 2.5);
        battery.setRechargeable(true);
        return battery;
    }

    @Bean
    public Product cdrw() {
        Disc cdrw = new Disc("CD-RW", 1.5);
        cdrw.setCapacity(700);
        return cdrw;
    }

    @Bean
    public Product dvdrw() {
        Disc dvdrw = new Disc("DVD-RW", 3.0);
        dvdrw.setCapacity(700);
        return dvdrw;
    }

    @Bean
    public Cashier cashier() {
        return new Cashier();
    }
}
