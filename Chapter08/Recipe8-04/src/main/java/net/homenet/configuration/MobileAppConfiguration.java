package net.homenet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.mobile.device.site.SitePreferenceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.view.LiteDeviceDelegatingViewResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("net.homenet")
public class MobileAppConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DeviceResolverHandlerInterceptor());
        registry.addInterceptor(new SitePreferenceHandlerInterceptor());
    }

    //@Override
    //public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    //    resolvers.add(new DeviceHandlerMethodArgumentResolver());
    //    resolvers.add(new SitePreferenceHandlerMethodArgumentResolver());
    //}

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(2);
        return viewResolver;
    }

    @Bean
    public LiteDeviceDelegatingViewResolver deviceDelegatingViewResolver() {
        LiteDeviceDelegatingViewResolver viewResolver = new LiteDeviceDelegatingViewResolver(viewResolver());
        viewResolver.setMobilePrefix("mobile/");
        viewResolver.setTabletPrefix("tablet/");
        viewResolver.setOrder(1);
        return viewResolver;
    }
}
