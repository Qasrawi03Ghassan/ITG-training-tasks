package com.infinite.employee_manager.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.infinite.employee_manager.Repositories.UserRepository;
import com.infinite.employee_manager.Services.UsersService;

import jakarta.servlet.DispatcherType;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


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
            .requestMatchers("/employees/add","/employees/edit/*","/employees/delete/*").hasRole("ADMIN")
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

    /*
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder pe){

        Iterable<com.infinite.employee_manager.Models.User> usersList =  usersService.getUsersDB();
         InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        for (com.infinite.employee_manager.Models.User myUser : usersList) {
           UserDetails user = User.withUsername(myUser.getUsername())
                .password(pe.encode(myUser.getPassword()))
                .roles(myUser.getRole())
                .build();
                manager.createUser(user);
        }
        return manager;
    }
    */   
    
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
