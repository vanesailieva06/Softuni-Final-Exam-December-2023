package com.example.bookstore.service.impl;

import com.example.bookstore.model.entity.Role;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.CurrentUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class BookStoreUserDetailsService  implements UserDetailsService {
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    public BookStoreUserDetailsService(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.example.bookstore.model.entity.User> user = userRepository
                .findByUsername(username);

        if (user.isPresent()){
            currentUser.setUsername(username);
            currentUser.setId(Objects.requireNonNull(userRepository.findByUsername(username).orElse(null)).getId());
        }
        return user
                .map(BookStoreUserDetailsService::map)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));
    }

    private static UserDetails map(com.example.bookstore.model.entity.User userEntity) {
        return User
                .withUsername(userEntity.getUsername())
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
