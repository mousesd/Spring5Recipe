package net.homenet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopConfiguration {
    @Bean
    public Product getBattery() {
        Battery battery = new Battery("AAA", 2.5);
        battery.setRechargeable(true);
        return battery;
    }

    @Bean
    public Product getDisc() {
        Disc disc = new Disc("CD-RW", 1.5);
        disc.setCapacity(700);
        return disc;
    }
}
