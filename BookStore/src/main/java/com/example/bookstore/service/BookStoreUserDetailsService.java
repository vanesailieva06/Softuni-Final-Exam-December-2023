package com.example.bookstore.service;

import com.example.bookstore.model.entity.Role;
import com.example.bookstore.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookStoreUserDetailsService  implements UserDetailsService {
    private final UserRepository userRepository;

    public BookStoreUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository
                .findByUsername(username)
                .map(BookStoreUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }

    private static UserDetails map(com.example.bookstore.model.entity.User userEntity) {
        return User
                .withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles().stream().map(BookStoreUserDetailsService::map).toList())
                .build();
    }

    private static GrantedAuthority map(Role role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role.getRoleType().name()
        );
    }
}
