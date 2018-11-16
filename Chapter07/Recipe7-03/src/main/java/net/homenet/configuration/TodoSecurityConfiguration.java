package net.homenet.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class TodoSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //# 1.Authenticate user with In-Memory definitions
        auth.inMemoryAuthentication()
            .withUser("mousesd@gmail.com").password("{noop}gmail").authorities("USER", "ADMIN")
            .and()
            .withUser("mousesd@outlook.com").password("{noop}outlook").authorities("USER")
            .and()
            .withUser("mousesd@icloud.com").password("{noop}icloud").disabled(true).authorities("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //# Form-based login
        http.authorizeRequests()
                .antMatchers("/todos*").hasAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/todos*").hasAuthority("ADMIN")
            .and().securityContext()
            .and().exceptionHandling()
            .and().servletApi()
            .and().formLogin()
                .loginPage("/login.jsp")
                .loginProcessingUrl("/login")
                .failureUrl("/login.jsp?error=true")
                .defaultSuccessUrl("/todos")
            .and().logout()
                .logoutSuccessUrl("/logout-success.jsp")
            .and().headers()
            .and().httpBasic().disable();
    }
}
