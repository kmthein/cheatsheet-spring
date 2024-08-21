package com.spring.service;

import com.spring.dto.ResponseDTO;
import com.spring.entity.User;

public interface UserService {
    User getUserById(int id);

    int updateUser(User user);

    ResponseDTO changePassword(int id, String oldPassword, String newPassword, String confirmPassword);
}
