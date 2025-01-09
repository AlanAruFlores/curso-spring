package com.security.crud.repository;

import com.security.crud.model.Role;
import java.util.Optional;

import com.security.crud.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
