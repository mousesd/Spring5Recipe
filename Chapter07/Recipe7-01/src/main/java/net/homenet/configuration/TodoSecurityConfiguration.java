package net.homenet.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class TodoSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("mousesd@gmail.com").password("{noop}gmail").authorities("USER")
            .and()
            .withUser("mousesd@outlook.com").password("{noop}outlook").authorities("USER", "ADMIN");
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

        //# 3.
        http.authorizeRequests()
            .antMatchers("/todos*").hasAuthority("USER")
            .antMatchers(HttpMethod.DELETE, "/todos*").hasAuthority("ADMIN")
            .and()
            .formLogin();

        //# 아래 코드를 이용 CsrfTokenRepository 변경이 가능, 그렇다면 기본값은?
        //HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        //repository.setSessionAttributeName("csrf_token");
        //repository.setParameterName("csrf_toke");
        //http.csrf().csrfTokenRepository(repository);
    }
}
