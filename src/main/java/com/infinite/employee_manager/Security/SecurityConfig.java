package com.infinite.employee_manager.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.infinite.employee_manager.Services.UsersService;

import jakarta.servlet.DispatcherType;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private final UsersService usersService;
    public SecurityConfig(UsersService usersService){
        this.usersService=usersService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

    return http
    .authorizeHttpRequests(authorizeHttpRequestsCustomizer ->
        authorizeHttpRequestsCustomizer
            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() //Important for JSP views

            .requestMatchers("/login","/error").permitAll()
            .requestMatchers("/employees/add","/employees/edit/*","/employees/delete/*").hasAuthority("ADMIN") // hasRole will cause 403 error on the admin operations due to "ROLE_" incrementing

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
        .logoutSuccessUrl("/login?logout")

        .deleteCookies("JSESSIONID")
        .invalidateHttpSession(true)
        .clearAuthentication(true)

        .permitAll()
    )
    .csrf(csrf->csrf.disable())
    .build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(usersService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
