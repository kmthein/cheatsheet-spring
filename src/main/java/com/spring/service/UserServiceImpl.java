package com.spring.service;

import com.spring.dto.ResponseDTO;
import com.spring.entity.User;
import com.spring.jbcrypt.BCrypt;
import com.spring.repository.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInterface userRepo;
    @Override
    public User getUserById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public int updateUser(User user) {
        User userExist = userRepo.findById(user.getId());
        if(userExist != null) {
            userExist.setName(user.getName());
            userExist.setDescription(user.getDescription());
            userExist.setWebsite(user.getWebsite());
        }
        return userRepo.updateUser(userExist);
    }

    @Override
    public ResponseDTO changePassword(int id, String oldPassword, String newPassword, String confirmPassword) {
        User currentUser = userRepo.findById(id);
        ResponseDTO responseDTO = new ResponseDTO();
        if(currentUser != null) {
            String storedHashPassword = currentUser.getPassword();
            Boolean passValid = BCrypt.checkpw(oldPassword, storedHashPassword);
            if(!passValid) {
                responseDTO.setMessage("Old Password was not correct");
                responseDTO.setStatus("403");
                return responseDTO;
            } else if(newPassword.length() < 6 | newPassword.length() > 20) {
                responseDTO.setMessage("Password must be between 6 and 20 characters");
                responseDTO.setStatus("403");
                return responseDTO;
            } else if(!newPassword.equals(confirmPassword)) {
                responseDTO.setMessage("Confirm Password not match");
                responseDTO.setStatus("401");
                return responseDTO;
            } else {
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                currentUser.setPassword(hashedPassword);
                userRepo.updateUser(currentUser);
                responseDTO.setStatus("200");
            }
        }
        return responseDTO;
    }
}
