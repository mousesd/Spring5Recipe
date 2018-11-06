package net.homenet.configuration;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MeasurementInterceptor implements AsyncHandlerInterceptor {

    private static final String START_TIME = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request
        , HttpServletResponse response
        , Object handler) {

        if (request.getAttribute(START_TIME) == null) {
            request.setAttribute(START_TIME, System.currentTimeMillis());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request
        , HttpServletResponse response
        , Object handler
        , ModelAndView modelAndView) {

        long startTime = (long) request.getAttribute(START_TIME);
        request.removeAttribute(START_TIME);
        long endTime = System.currentTimeMillis();

        System.out.println("Response processing time " + (endTime - startTime) + "ms on postHandle()");
        System.out.println("Response processing thread: " + Thread.currentThread().getName());
        modelAndView.addObject("handlingTime", endTime - startTime);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request
        , HttpServletResponse response
        , Object handler) {

        long startTime = (Long) request.getAttribute(START_TIME);
        request.setAttribute(START_TIME, System.currentTimeMillis());
        long endTime = System.currentTimeMillis();

        System.out.println("Response processing time " + (endTime - startTime) + "ms on afterConcurrentHandlingStarted()");
        System.out.println("Response processing thread: " + Thread.currentThread().getName());
    }
}
