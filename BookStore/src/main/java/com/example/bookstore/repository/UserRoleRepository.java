package com.example.bookstore.repository;

import com.example.bookstore.model.entity.Role;
import com.example.bookstore.model.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findAllByRoleTypeIn(List<RoleType> roles);
}
