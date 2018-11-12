package net.homenet.controller;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/facebook")
public class FacebookController {
    private final ConnectionRepository repository;
    private final Facebook facebook;

    public FacebookController(ConnectionRepository repository, Facebook facebook) {
        this.repository = repository;
        this.facebook = facebook;
    }

    @GetMapping(value = "/profile")
    public String showFacebookProfile(Model model) {
        //# NOTE: repository.getPrimaryConnection(Facebook.class) 호출시 아래 예외 발생
        //#       Facebook 에 로그인후에는 발생하지 않음
        //# org.springframework.social.connect.NotConnectedException: Not connected to provider 'facebook'
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

        //# NOTE: Facebook 로그인후 connection.fetchUserProfile() 호출시 아래 예외 발생
        //#       Facebook API 변경사항이 있는거 같음(https://stackoverflow.com/questions/39890885/error-message-is-12-bio-field-is-deprecated-for-versions-v2-8-and-higher)
        //# org.springframework.social.UncategorizedApiException: (#12) bio field is deprecated for versions v2.8 and higher
        //#     at org.springframework.social.facebook.api.impl.FacebookErrorHandler.handleFacebookError(FacebookErrorHandler.java:91)
        //#     at org.springframework.social.facebook.api.impl.FacebookErrorHandler.handleError(FacebookErrorHandler.java:59)
        //#     at org.springframework.web.client.ResponseErrorHandler.handleError(ResponseErrorHandler.java:63)
        //#     at org.springframework.web.client.RestTemplate.handleResponse(RestTemplate.java:775)
        //#     at org.springframework.web.client.RestTemplate.doExecute(RestTemplate.java:728)
        //#     at org.springframework.web.client.RestTemplate.execute(RestTemplate.java:702)
        //#     at org.springframework.web.client.RestTemplate.getForObject(RestTemplate.java:350)
        //#     at org.springframework.social.facebook.api.impl.FacebookTemplate.fetchObject(FacebookTemplate.java:225)
        //#     at org.springframework.social.facebook.api.impl.FacebookTemplate.fetchObject(FacebookTemplate.java:220)
        //#     at org.springframework.social.facebook.api.impl.UserTemplate.getUserProfile(UserTemplate.java:53)
        //#     at org.springframework.social.facebook.api.impl.UserTemplate.getUserProfile(UserTemplate.java:49)
        //#     at org.springframework.social.facebook.connect.FacebookAdapter.fetchUserProfile(FacebookAdapter.java:50)
        //#     at org.springframework.social.facebook.connect.FacebookAdapter.fetchUserProfile(FacebookAdapter.java:30)
        //#     at org.springframework.social.connect.support.AbstractConnection.fetchUserProfile(AbstractConnection.java:111)
        //#     at net.homenet.controller.FacebookController.showFacebookProfile(FacebookController.java:53)
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
        Connection<Facebook> connection = repository.getPrimaryConnection(Facebook.class);
        model.addAttribute("profile", connection.fetchUserProfile());
        return "facebook-info";
    }

    @PostMapping
    public String facebook(@RequestParam("status") String status) {
        return "redirect:/facebook";
    }
}
