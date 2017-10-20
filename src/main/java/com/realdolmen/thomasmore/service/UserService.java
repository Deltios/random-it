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

    public void createUser(String voornaam, String familienaam, String wachtwoord) {
        User user = new User();
        user.setVoornaam(voornaam);
        user.setFamilienaam(familienaam);
        user.setWachtwoord(wachtwoord);

        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
