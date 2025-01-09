package com.security.crud.service.impl;

import com.security.crud.model.User;
import com.security.crud.repository.UserRepository;
import com.security.crud.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUserName(String userName) {
        return this.userRepository.findByUsername(userName).orElse(null);
    }
}
