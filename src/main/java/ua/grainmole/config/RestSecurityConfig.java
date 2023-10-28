package ua.grainmole.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class RestSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        try {
            return http
                    .authorizeHttpRequests(req ->
                            req.requestMatchers(
                                            "/login",
                                            "/reg",
                                            "/sticks/forGateway/**")
                                    .permitAll()
                                    .anyRequest().authenticated())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
