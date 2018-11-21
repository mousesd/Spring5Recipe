package net.homenet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
            .pathMatchers("/welcome", "/welcome/**").permitAll()
            .pathMatchers("/reservation*").hasAuthority("USER")
            .pathMatchers("/reservationForm").access(this::userEditAllowed)
            .anyExchange().authenticated()
            .and()
            .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("mousesd@gmail.com")
            .password("gmail")
            .authorities("USER")
            .build();
        UserDetails user2 = User.withUsername("mousesd@outlook.com")
            .password("{noop}outlook")
            .authorities("USER", "ADMIN")
            .build();
        return new MapReactiveUserDetailsService(user1, user2);
    }

    private Mono<AuthorizationDecision> userEditAllowed(Mono<Authentication> authentication, AuthorizationContext context) {
        return authentication
            .map(a -> context.getVariables().get("user").equals(a.getName()))
            .map(AuthorizationDecision::new);
    }
}
