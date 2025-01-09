package com.security.crud.service.impl;

import com.security.crud.model.Role;
import com.security.crud.model.RoleEnum;
import com.security.crud.model.User;
import com.security.crud.repository.RoleRepository;
import com.security.crud.repository.UserRepository;
import com.security.crud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserByUserName(String userName) {
        return this.userRepository.findByUsername(userName).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        Role role = this.roleRepository.findByName(RoleEnum.USER).orElse(null);
        if(role == null) return;

        user.setRoles(Set.of(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }
}
