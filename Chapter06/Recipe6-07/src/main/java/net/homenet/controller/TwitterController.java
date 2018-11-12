package net.homenet.controller;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/twitter")
public class TwitterController {
    private final ConnectionRepository repository;

    public TwitterController(ConnectionRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/profile")
    public String showTwitterProfile(Model model) {
        //# NOTE: repository.getPrimaryConnection(Twitter.class) 호출시 아래 예외 발생
        //# org.springframework.social.connect.NotConnectedException: Not connected to provider 'twitter'
        //#     at org.springframework.social.connect.mem.InMemoryConnectionRepository.getPrimaryConnection(InMemoryConnectionRepository.java:99)
        //#     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        //#     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        //#     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        //#     at java.lang.reflect.Method.invoke(Method.java:498)
        //#     at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)
        //#     at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:197)
        //#     at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
        //#     at org.springframework.aop.support.DelegatingIntroductionInterceptor.doProceed(DelegatingIntroductionInterceptor.java:136)
        //#     at org.springframework.aop.support.DelegatingIntroductionInterceptor.invoke(DelegatingIntroductionInterceptor.java:124)
        //#     at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:185)
        //#     at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)
        //#     at com.sun.proxy.$Proxy25.getPrimaryConnection(Unknown Source)
        //#     at net.homenet.controller.FacebookController.showFacebookProfile(FacebookController.java:22)
        //#     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        //#     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        //#     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        //#     at java.lang.reflect.Method.invoke(Method.java:498)
        //#     at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:209)
        //#     at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:136)
        //#     at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:102)
        //#     at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877)
        //#     at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783)
        //#     at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
        //#     at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991)
        //#     at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925)
        //#     at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974)
        //#     at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:866)
        Connection<Twitter> connection = repository.getPrimaryConnection(Twitter.class);
        model.addAttribute("profile", connection.fetchUserProfile());
        return "twitter-info";
    }
}
