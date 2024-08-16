package com.spring.service;

import com.spring.dto.LoginDTO;
import com.spring.dto.ResponseDTO;
import com.spring.entity.User;
import com.spring.jbcrypt.BCrypt;
import com.spring.model.Role;
import com.spring.repository.UserInterface;
import com.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserInterface userRepo;

    @Override
    public ResponseDTO registerUser(User user) {
        int result = 0;
        User userExist = userRepo.findByEmail(user.getEmail());
        ResponseDTO responseDTO = new ResponseDTO();
        if (user.getPassword().length() < 6 || user.getPassword().length() > 20) {
            responseDTO.setStatus("403");
            responseDTO.setMessage("Password must be between 6 and 20 characters");
            return responseDTO;
        } else if (userExist != null) {
            responseDTO.setStatus("409");
            responseDTO.setMessage("Email already existed.");
            return responseDTO;
        } else {
            String password = user.getPassword();
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(hashedPassword);
            user.setRole(Role.USER);
            result = userRepo.saveUser(user);
            if (result > 0) {
                responseDTO.setStatus("201");
                responseDTO.setMessage("User register successful");
            }
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO loginUser(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        User userExist = userRepo.findByEmail(email);
        ResponseDTO responseDTO = new ResponseDTO();
        if (userExist == null) {
            responseDTO.setStatus("404");
            responseDTO.setMessage("Email not existed");
            return responseDTO;
        }
        String storedHashPassword = userExist.getPassword();
        Boolean passValid = BCrypt.checkpw(password, storedHashPassword);
        if (!passValid) {
            responseDTO.setStatus("403");
            responseDTO.setMessage("Email or password not correct");
            return responseDTO;
        }
        userExist.setPassword(password);
        responseDTO.setUser(userExist);
        responseDTO.setStatus("200");
        responseDTO.setMessage("Login successful");
        return responseDTO;
    }
}
