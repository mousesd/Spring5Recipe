package net.homenet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebFlux
@ComponentScan("net.homenet")
public class WebFluxConfiguration implements WebFluxConfigurer {
    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    @Bean
    public ISpringWebFluxTemplateEngine thymeleafTemplateEngine() {
        SpringWebFluxTemplateEngine engine = new SpringWebFluxTemplateEngine();
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(thymeleafTemplateResolver());
        return engine;
    }

    @Bean
    public ThymeleafReactiveViewResolver thymeleafReactiveViewResolver() {
        ThymeleafReactiveViewResolver resolver = new ThymeleafReactiveViewResolver();
        resolver.setTemplateEngine(thymeleafTemplateEngine());
        resolver.setResponseMaxChunkSizeBytes(16384);
        return resolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafReactiveViewResolver());
    }
}
