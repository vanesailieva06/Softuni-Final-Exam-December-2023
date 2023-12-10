package com.example.bookstore.service.impl;

import com.example.bookstore.model.dto.UserRegisterDto;
import com.example.bookstore.model.dto.UserViewDto;
import com.example.bookstore.model.entity.Role;
import com.example.bookstore.model.entity.User;
import com.example.bookstore.model.entity.enums.RoleType;
import com.example.bookstore.repository.RoleRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CurrentUser currentUser;
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.currentUser = currentUser;
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        User user = modelMapper.map(userRegisterDto, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Role role = new Role();
        role.setRoleType(RoleType.USER);
        roleRepository.save(role);
        userRepository.save(user);
    }

    @Override
    public UserViewDto findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return modelMapper.map(user, UserViewDto.class);
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<UserViewDto> getAll() {

        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
