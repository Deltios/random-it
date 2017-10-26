package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.Klant;
import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.repository.UserRepository;
import com.realdolmen.thomasmore.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.faces.bean.ManagedProperty;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSession userSession;


    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
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
        nieuweKlant.setUserLevel(1);
        String hashedWachtwoord = passwordEncoder.encode(nieuweKlant.getWachtwoord());
        nieuweKlant.setWachtwoord(hashedWachtwoord);

        userRepository.save(nieuweKlant);
    }

    public User authenticateUser(String email, String wachtwoord){
        User gebruiker = userRepository.findByEmail(email);
        if (!(gebruiker==null)){
            if ((passwordEncoder.matches(wachtwoord, gebruiker.getWachtwoord()))){
                return gebruiker;
            }
        }
        return null;
    }
    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User getUser(long id){
        User user = userRepository.findOne(id);
        return user;
    }

    public Long setUserSession(User huidigeUser) {

        return userSession.setUserSession(huidigeUser);
    }
    public void logoutUser(){
        userSession.logoutSession();
    }
}
