package net.homenet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;

@Configuration
public class ViewResolverConfiguration {

    //private final ResourceLoader resourceLoader;
    //
    //public ViewResolverConfiguration(ResourceLoader resourceLoader) {
    //    this.resourceLoader = resourceLoader;
    //}

    //@Bean
    //public XmlViewResolver xmlViewResolver() {
    //    XmlViewResolver viewResolver = new XmlViewResolver();
    //    viewResolver.setLocation(resourceLoader.getResource("/WEB-INF/views.xml"));
    //    return viewResolver;
    //}

    @Bean
    public ResourceBundleViewResolver viewResolver() {
        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setOrder(0);
        viewResolver.setBasename("court-views");
        return viewResolver;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
