package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(String username, String password, int userLevel) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserLevel(userLevel);

        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
