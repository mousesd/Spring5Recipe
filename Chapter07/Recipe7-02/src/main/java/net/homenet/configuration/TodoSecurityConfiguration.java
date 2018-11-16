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
    public TodoSecurityConfiguration() {
        super(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("mousesd@gmail.com").password("{noop}gmail").authorities("ROLE_USER")
            .and()
            .withUser("mousesd@outlook.com").password("{noop}outlook").authorities("ROLE_USER", "ROLE_ADMIN")
            .and()
            .withUser("guest").password("{noop}guest").authorities("ROLE_GUEST");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //# 1.
        //http.authorizeRequests()
        //    .anyRequest().authenticated()
        //    .and()
        //    .formLogin()
        //    .and()
        //    .httpBasic();

        //# 2.
        //http.authorizeRequests()
        //    .antMatchers("/todos*").hasAuthority("USER")
        //    .antMatchers(HttpMethod.DELETE, "/todos*").hasAuthority("ADMIN")
        //    .and()
        //    .formLogin()
        //    .and()
        //    .csrf().disable();

        //# 아래 코드를 이용 CsrfTokenRepository 변경이 가능, 그렇다면 기본값은?
        //HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        //repository.setSessionAttributeName("csrf_token");
        //repository.setParameterName("csrf_toke");
        //http.csrf().csrfTokenRepository(repository);

        //# 3.Http basic authentication
        //http.authorizeRequests()
        //    .antMatchers("/todos*").hasAuthority("USER")
        //    .antMatchers(HttpMethod.DELETE, "/todos*").hasAuthority("ADMIN")
        //    .and().securityContext()
        //    .and().exceptionHandling()
        //    .and().servletApi()
        //    .and().httpBasic();

        //# 4.Form-based login
        http.authorizeRequests()
                .antMatchers("/todos*").hasAuthority("ROLE_USER")
                .antMatchers(HttpMethod.DELETE, "/todos*").hasAuthority("ROLE_ADMIN")
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
            .and().anonymous()
                .principal("guest")
                .authorities("ROLE_GUEST")
            .and().rememberMe();
    }
}
