package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.Klant;
import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(String voornaam, String familienaam, String adres, String gemeente, String postcode, String email, String wachtwoord, String telefoon) {
        User user = new User();
        user.setVoornaam(voornaam);
        user.setFamilienaam(familienaam);
        user.setAdres(adres);
        user.setGemeente(gemeente);
        user.setPostcode(postcode);
        user.setEmail(email);
        user.setWachtwoord(wachtwoord);
        user.setTelefoon(telefoon);

        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void registerUser(User newUser) {
        newUser.setUserLevel(1);
        userRepository.save(newUser);
    }

    public void registerKlant(Klant nieuweKlant) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        nieuweKlant.setUserLevel(1);

        String hashedWachtwoord = encoder.encode(nieuweKlant.getWachtwoord());
        nieuweKlant.setWachtwoord(hashedWachtwoord);

        userRepository.save(nieuweKlant);
    }

    public boolean checkLogin(String email, String wachtwoord){
        User gebruiker = userRepository.findByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(wachtwoord, gebruiker.getWachtwoord())){
            return true;
        }
        else{
            return false;
        }
    }
    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User getUser(long id){
        User user = userRepository.findOne(id);
        return user;
    }
}
