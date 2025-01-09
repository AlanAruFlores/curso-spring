package com.security.crud.service;

import com.security.crud.model.User;

public interface IUserService {
    User getUserByUserName(String userName);
    void saveUser(User user);
}
