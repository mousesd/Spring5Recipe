package net.homenet.configuration;

import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;

//# 1.
//public class WebFluxInitializer implements WebApplicationInitializer {
//    @Override
//    public void onStartup(ServletContext servletContext) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebFluxConfiguration.class);
//        HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();
//
//        ServletHttpHandlerAdapter adapter = new ServletHttpHandlerAdapter(handler);
//        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher-handler", adapter);
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/");
//        registration.setAsyncSupported(true);
//    }
//}

//# 2.
public class WebFluxInitializer extends AbstractReactiveWebInitializer {

    @Override
    protected Class<?>[] getConfigClasses() {
        return new Class<?>[]{ WebFluxConfiguration.class };
    }
}