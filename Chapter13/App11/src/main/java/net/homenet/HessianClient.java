package net.homenet;

import net.homenet.configuration.HessianClientWeatherConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class HessianClient {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HessianClientWeatherConfiguration.class);
        WeatherService weatherService = context.getBean(WeatherService.class);

        //# 아래 코드를 실행시 다음 예외가 발생
        //Exception in thread "main" org.springframework.remoting.RemoteAccessException: Cannot access Hessian remote service at [http://localhost:8080/weather]; nested exception is com.caucho.hessian.io.HessianProtocolException: 500: java.io.IOException: Server returned HTTP response code: 500 for URL: http://localhost:8080/weather
        //    at org.springframework.remoting.caucho.HessianClientInterceptor.convertHessianAccessException(HessianClientInterceptor.java:197)
        //    at org.springframework.remoting.caucho.HessianClientInterceptor.invoke(HessianClientInterceptor.java:171)
        //    at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)
        //    at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)
        //    at com.sun.proxy.$Proxy12.getTemperators(Unknown Source)
        //    at net.homenet.HessianClient.main(HessianClient.java:16)
        //    Caused by: com.caucho.hessian.io.HessianProtocolException: 500: java.io.IOException: Server returned HTTP response code: 500 for URL: http://localhost:8080/weather
        //    at com.caucho.hessian.client.HessianProxy.invoke(HessianProxy.java:185)
        //    at com.sun.proxy.$Proxy11.getTemperators(Unknown Source)
        //    at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        //    at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        //    at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        //    at java.lang.reflect.Method.invoke(Method.java:498)
        //    at org.springframework.remoting.caucho.HessianClientInterceptor.invoke(HessianClientInterceptor.java:165)
        //Caused by: java.io.IOException: Server returned HTTP response code: 500 for URL: http://localhost:8080/weather
        //    at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
        //    at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
        //    at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        //    at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
        //    at sun.net.www.protocol.http.HttpURLConnection$10.run(HttpURLConnection.java:1944)
        //    at sun.net.www.protocol.http.HttpURLConnection$10.run(HttpURLConnection.java:1939)
        //    at java.security.AccessController.doPrivileged(Native Method)
        //    at sun.net.www.protocol.http.HttpURLConnection.getChainedException(HttpURLConnection.java:1938)
        //    at sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1508)
        //    at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1492)
        //    at com.caucho.hessian.client.HessianProxy.invoke(HessianProxy.java:167)
        //Caused by: java.io.IOException: Server returned HTTP response code: 500 for URL: http://localhost:8080/weather
        //    at sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1894)
        //    at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1492)
        //    at java.net.HttpURLConnection.getResponseCode(HttpURLConnection.java:480)
        //    at com.caucho.hessian.client.HessianProxy.invoke(HessianProxy.java:158)
        List<TemperatureInfo> temperatures = weatherService.getTemperators("Seoul", Collections.singletonList(new Date()));
        for (TemperatureInfo temperature : temperatures) {
            System.out.println(temperature);
        }
    }
}
