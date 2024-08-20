package com.spring.service;

import com.spring.entity.User;
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
}
