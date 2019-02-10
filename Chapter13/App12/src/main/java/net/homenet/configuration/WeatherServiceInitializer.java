package net.homenet.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WeatherServiceInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ HttpInvokerServerWeatherConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/*" };
    }
}
