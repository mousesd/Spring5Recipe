package net.homenet.s1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SuppressWarnings("SpringFacetCodeInspection")
@Configuration
@ComponentScan("net.homenet.s1")
@PropertySource("classpath:discounts.properties")
public class ShopConfiguration {

    @Value("${specialcustomer.discount:0}")
    private double specialCustomerDiscount;
    @Value("${summer.discount:0}")
    private double specialSummerDiscount;
    @Value("${endofyear.discount:0}")
    private double specialEndofyearDiscount;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Product battery() {
        Battery battery = new Battery("AAA", 2.5, specialCustomerDiscount);
        battery.setRechargeable(true);
        return battery;
    }

    @Bean
    public Product cdrw() {
        Disc cdrw = new Disc("CD-RW", 1.5, specialSummerDiscount);
        cdrw.setCapacity(700);
        return cdrw;
    }

    @Bean
    public Product dvdrw() {
        Disc dvdrw = new Disc("DVD-RW", 3.0, specialEndofyearDiscount);
        dvdrw.setCapacity(700);
        return dvdrw;
    }
}
