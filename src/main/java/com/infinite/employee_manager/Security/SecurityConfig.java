package com.infinite.employee_manager.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(res->res
            .requestMatchers("/login","/css/**","/js/**").permitAll()
            .anyRequest().authenticated()
            )
            .formLogin(form -> form
            .loginPage("/login").permitAll()
            );
            
            
        return http.build();
    }
}
