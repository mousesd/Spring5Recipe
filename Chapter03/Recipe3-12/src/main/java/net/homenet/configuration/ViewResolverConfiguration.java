package net.homenet.configuration;

import net.homenet.service.ReservationService;
import net.homenet.service.SportTypeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Configuration
public class ViewResolverConfiguration implements WebMvcConfigurer {

    private final ReservationService reservationService;

    public ViewResolverConfiguration(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("html", MediaType.TEXT_HTML);
        mediaTypes.put("pdf", MediaType.valueOf("application/json"));
        mediaTypes.put("xls", MediaType.valueOf("application/vnd.ms-excel"));
        mediaTypes.put("xml", MediaType.APPLICATION_XML);
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        configurer.mediaTypes(mediaTypes);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SportTypeConverter(reservationService));
    }

    //# TODO: 아래 2가지 정상동작하지 않음. 해결할것!
    //# 1.확장자가 .pdf 인 경우는 pdfViewResolver가 동작
    //# 2.확장자가 .XLS 인 경우는 xlsViewResolver가 동작
    @Bean
    public ResourceBundleViewResolver pdfViewResolver() {
        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setOrder(0);
        viewResolver.setBasename("court-view-pdf");
        return viewResolver;
    }

    @Bean
    public ResourceBundleViewResolver xlsViewResolver() {
        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setOrder(0);
        viewResolver.setBasename("court-view-xls");
        return viewResolver;
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName("language");
        resolver.setCookieMaxAge(3600);
        resolver.setDefaultLocale(new Locale("en"));
        return resolver;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setContentNegotiationManager(manager);
        return viewResolver;
    }
}
