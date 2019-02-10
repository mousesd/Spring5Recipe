package net.homenet.configuration;

import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

public class WeatherWebAppInitializer extends AbstractAnnotationConfigMessageDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ SpringWsWeatherConfiguration.class };
    }
}
