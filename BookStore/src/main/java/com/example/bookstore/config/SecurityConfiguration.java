package com.example.bookstore.config;

import com.example.bookstore.model.entity.enums.RoleType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.nio.file.Path;

import static org.springframework.security.authorization.AuthenticatedAuthorizationManager.rememberMe;

@Configuration
public class SecurityConfiguration {

    private final String rememberMeKey;

    public SecurityConfiguration(
            @Value("${bookstore.remember.me.key}") String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                authorizeRequests ->
                        authorizeRequests.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        // Allow anyone to see the home page, the registration page and the login form
                                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                        .requestMatchers("/", "/users/login", "/users/register", "/users/login-error", "/about"
                        ,"/contacts", "/books/all", "/learn-more", "/books/science-fiction",
                                "/books/romance", "/books/comedy",
                                "/books/horror", "/books/history",
                                "/books/thriller", "/books/action",
                                "/books/fantasy").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/offer/**").permitAll() authenticated
                        .requestMatchers("/error").permitAll()
                                .requestMatchers("/books/api").permitAll()
                                .requestMatchers("/book/add", "/admin").hasRole(RoleType.ADMIN.name())
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
        }).rememberMe(
                rememberMe ->
                        rememberMe
                                .key(rememberMeKey)
                                .rememberMeParameter("rememberme")
                                .rememberMeCookieName("rememberme")
        ).build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}
