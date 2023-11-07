package com.example.bookstore.service.impl;

import com.example.bookstore.model.dto.UserRegisterDto;
import com.example.bookstore.model.entity.Role;
import com.example.bookstore.model.entity.User;
import com.example.bookstore.model.entity.enums.AudienceType;
import com.example.bookstore.model.entity.enums.RoleType;
import com.example.bookstore.repository.RoleRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.RoleService;
import com.example.bookstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        User user = modelMapper.map(userRegisterDto, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Role role = new Role();
        role.setRoleType(RoleType.USER);
        roleRepository.save(role);
        user.setRoles(Set.of(role));
        if (user.getAge() <= 12){
            user.setAudienceType(AudienceType.KIDS);
        } else if (user.getAge() <= 19) {
            user.setAudienceType(AudienceType.TEENAGERS);
        } else{
            user.setAudienceType(AudienceType.ADULT);
        }

        userRepository.save(user);
    }
}
