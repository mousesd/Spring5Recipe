package net.homenet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class TodoSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //# 1.Authenticate user with In-Memory definitions
        //auth.inMemoryAuthentication()
        //    .withUser("mousesd@gmail.com").password("{noop}gmail").authorities("USER", "ADMIN")
        //    .and()
        //    .withUser("mousesd@outlook.com").password("{noop}outlook").authorities("USER")
        //    .and()
        //    .withUser("mousesd@icloud.com").password("{noop}icloud").disabled(true).authorities("USER", "ADMIN");

        //# 2.Authenticate users against a Database
        // SELECT username, password, enabled
        //   FROM users
        //  WHERE username = ?
        //
        // SELECT username, authority
        //   FROM authorities
        //  WHERE username = ?
        //auth.jdbcAuthentication()
        //    .dataSource(dataSource());

        //# 3.Supports using custom SQL statement to query for a legacy database
        auth.jdbcAuthentication()
            .dataSource(dataSource())
            .usersByUsernameQuery("SELECT username, password, 'true' as enabled FROM member WHERE username = ?")
            .authoritiesByUsernameQuery("SELECT member.username, member_role.role as authorities "
                + "FROM member, member_role "
                + "WHERE member.username = ? AND member.id = member_role.member_id");
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

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .setName("board")
            .addScript("classpath:/schema.sql")
            .addScript("classpath:/data.sql")
            .build();
    }
}
