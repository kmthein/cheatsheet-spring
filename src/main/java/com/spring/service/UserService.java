package com.spring.service;

import com.spring.entity.User;

public interface UserService {
    User getUserById(int id);

    int updateUser(User user);
}
