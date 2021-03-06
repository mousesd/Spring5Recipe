package net.homenet.s2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;

@SuppressWarnings("SpringFacetCodeInspection")
@Configuration
@ComponentScan("net.homenet.s2")
@PropertySource("classpath:discounts.properties")
public class ShopConfiguration {

    @Value("${specialcustomer.discount:0}")
    private double specialCustomerDiscount;
    @Value("${summer.discount:0}")
    private double specialSummerDiscount;
    @Value("${endofyear.discount:0}")
    private double specialEndofyearDiscount;
    @Value("classpath:banner.txt")
    private Resource banner;

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

    @Bean
    public BannerLoader bannerLoader() {
        BannerLoader loader = new BannerLoader();
        loader.setBanner(banner);
        return loader;
    }
}
