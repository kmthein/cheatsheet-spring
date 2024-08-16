package com.spring.service;

import com.spring.dto.LoginDTO;
import com.spring.dto.ResponseDTO;
import com.spring.entity.User;

public interface AuthService {
    ResponseDTO registerUser(User user);

    ResponseDTO loginUser(LoginDTO loginDTO);
}
