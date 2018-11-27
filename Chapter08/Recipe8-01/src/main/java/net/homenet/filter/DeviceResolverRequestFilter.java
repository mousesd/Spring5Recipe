package net.homenet.filter;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeviceResolverRequestFilter extends OncePerRequestFilter {
    private static final String CURRENT_DEVICE_ATTRIBUTE = "currentDevice";
    private static final String DEVICE_MOBILE = "Mobile";
    private static final String DEVICE_TABLET = "Tablet";
    private static final String DEVICE_NORMAL = "Normal";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
        , FilterChain filterChain) throws ServletException, IOException {

        String userAgent = request.getHeader("User-Agent");
        String device = DEVICE_NORMAL;

        if (StringUtils.hasText(userAgent)) {
            userAgent = userAgent.toLowerCase();
            if (userAgent.contains("android")) {
                device = userAgent.contains("mobile") ? DEVICE_MOBILE : DEVICE_TABLET;
            } else if (userAgent.contains("ipad") || userAgent.contains("playbook") || userAgent.contains("kindle")) {
                device = DEVICE_TABLET;
            } else if (userAgent.contains("mobil") || userAgent.contains("ipod")) {
                device = DEVICE_MOBILE;
            }
        }

        request.setAttribute(CURRENT_DEVICE_ATTRIBUTE, device);
        filterChain.doFilter(request, response);
    }
}
