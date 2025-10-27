package com.infinite.employee_manager.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

    return http
    .authorizeHttpRequests(authorizeHttpRequestsCustomizer ->
        authorizeHttpRequestsCustomizer
            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
            .requestMatchers("/login","/error").permitAll()
            .requestMatchers("/employees/add","/employees/edit","/employees/delete").hasRole("ADMIN")
            .anyRequest().authenticated()
    )
    .formLogin(form ->
        form
            .loginPage("/login")
            .loginProcessingUrl("/perform-login")
            .defaultSuccessUrl("/employees", true)
            .failureUrl("/login?error")
            .permitAll()
    )
    .logout(logout -> 
    logout
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login")
        .permitAll()
    )
    .csrf(csrf->csrf.disable())
    .build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder pe){
        UserDetails user = User.withUsername("test1")
            .password(pe.encode("testPass"))
            .roles("USER")
            .build();
        
            return new InMemoryUserDetailsManager(user);
    }   

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
