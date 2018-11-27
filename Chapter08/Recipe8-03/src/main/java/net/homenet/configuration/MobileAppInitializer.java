package net.homenet.configuration;

import org.springframework.mobile.device.DeviceResolverRequestFilter;
import org.springframework.mobile.device.site.SitePreferenceRequestFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class MobileAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ MobileAppConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }

    //# 1) Use DeviceResolverRequestFilter and SitePreferenceRequestFilter!
    //@Override
    //protected Filter[] getServletFilters() {
    //    return new Filter[]{
    //        new DeviceResolverRequestFilter(),
    //        new SitePreferenceRequestFilter()
    //    };
    //}
}
