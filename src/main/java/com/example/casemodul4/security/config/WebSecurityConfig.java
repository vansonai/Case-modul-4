package com.example.casemodul4.security.config;
import com.example.casemodul4.repository.IUserRepository;
import com.example.casemodul4.security.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private IUserRepository userRepository;


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService(userRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/home","/").permitAll()
////                        .requestMatchers("/customers/**").hasRole("CUSTOMER")
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .defaultSuccessUrl("/home")
//                        .permitAll()
//                )
//                .logout((logout) -> logout.permitAll());
//
//        return http.build();
        http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/", "/home", "/login-register","/register","/img/**","css/**").permitAll()
                                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/customers/**").hasAuthority("ROLE_CUSTOMER")

                ).formLogin(
                        form -> form
                                .loginPage("/login-register")
                                .loginProcessingUrl("/login-register")
                                .successHandler(successHandler())
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/admin");
            } else if (authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_CUSTOMER"))) {
                response.sendRedirect("/customers");
            }
        };
    }
}
