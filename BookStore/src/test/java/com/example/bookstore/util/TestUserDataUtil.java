package com.example.bookstore.util;

import com.example.bookstore.model.entity.User;
import com.example.bookstore.model.entity.enums.RoleType;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestUserDataUtil {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public User createTestUser(String username) {
        return createUser(username, List.of(RoleType.USER));
    }

    public User createTestAdmin(String username) {
        return createUser(username, List.of(RoleType.ADMIN));
    }

    private User createUser(String username, List<RoleType> roles) {

        var roleEntities = userRoleRepository.findAllByRoleTypeIn(roles);

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setRoles(roleEntities);
        newUser.setPassword("1234");
        newUser.setAge(22);
        newUser.setEmail("example@com");
        newUser.setFullName("Test Testov");

        return userRepository.save(newUser);
    }

    public void cleanUp() {
        userRepository.deleteAll();
    }
}
