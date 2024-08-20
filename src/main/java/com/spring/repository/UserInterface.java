package com.spring.repository;

import com.spring.entity.User;

public interface UserInterface {
    int saveUser(User user);

    User findByEmail(String email);

    User findById(int id);

    int updateUser(User user);
}
