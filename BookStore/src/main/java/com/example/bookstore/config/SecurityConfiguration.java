package com.example.bookstore.config;

import com.example.bookstore.model.entity.enums.RoleType;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.nio.file.Path;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                authorizeRequests ->
                        authorizeRequests.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // Allow anyone to see the home page, the registration page and the login form
                        .requestMatchers("/", "/users/login", "/users/register", "/users/login-error", "/about"
                        ,"/contacts", "/books/all").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/offer/**").permitAll() authenticated
                        .requestMatchers("/error").permitAll()
                                .requestMatchers("/books/api").permitAll()
                                .requestMatchers("/book/add").hasRole(RoleType.ADMIN.name())
                        // all other requests are authenticated.
                        .anyRequest().authenticated()
        )
        .formLogin(formLogin -> {
            formLogin.loginPage("/users/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/").failureForwardUrl("/users/login-error");
        }).logout(logout ->{
            logout.logoutUrl("/users/logout").logoutSuccessUrl("/")
                    .invalidateHttpSession(true);
        }).build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}
