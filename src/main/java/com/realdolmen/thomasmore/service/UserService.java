package com.realdolmen.thomasmore.service;

import com.realdolmen.thomasmore.data.Klant;
import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.data.Werknemer;
import com.realdolmen.thomasmore.repository.UserRepository;
import com.realdolmen.thomasmore.session.UserSession;
import org.primefaces.component.calendar.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.GregorianCalendar;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSession userSession;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    public void createUser(String voornaam, String familienaam, String adres, String gemeente, String postcode, String email, String wachtwoord, String telefoon, int userLevel) {
        User user = new User();
        user.setVoornaam(voornaam);
        user.setFamilienaam(familienaam);
        user.setAdres(adres);
        user.setGemeente(gemeente);
        user.setPostcode(postcode);
        user.setEmail(email);
        user.setWachtwoord(wachtwoord);
        user.setTelefoon(telefoon);
        user.setUserLevel(userLevel);

        userRepository.save(user);
    }

    public void createKlant(String voornaam, String familienaam, String adres, String gemeente, String postcode, String email, String wachtwoord, String telefoon, int userLevel, GregorianCalendar datumRegistratie) {
        Klant klant = new Klant();

        klant.setVoornaam(voornaam);
        klant.setFamilienaam(familienaam);
        klant.setAdres(adres);
        klant.setGemeente(gemeente);
        klant.setPostcode(postcode);
        klant.setEmail(email);
        klant.setWachtwoord(wachtwoord);
        klant.setTelefoon(telefoon);
        klant.setUserLevel(userLevel);
        klant.setDatumRegistratie(datumRegistratie);

        userRepository.save(klant);
    }

    public void createWerknemer(String voornaam, String familienaam, String adres, String gemeente, String postcode, String email, String wachtwoord, String telefoon, int userLevel) {
        Werknemer werknemer = new Werknemer();

        werknemer.setVoornaam(voornaam);
        werknemer.setFamilienaam(familienaam);
        werknemer.setAdres(adres);
        werknemer.setGemeente(gemeente);
        werknemer.setPostcode(postcode);
        werknemer.setEmail(email);
        werknemer.setWachtwoord(wachtwoord);
        werknemer.setTelefoon(telefoon);
        werknemer.setUserLevel(userLevel);

        userRepository.save(werknemer);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void registerUser(User newUser) {
        newUser.setUserLevel(1);
        userRepository.save(newUser);
    }
    public void updateUser(User user){
        String hashedWachtwoord = passwordEncoder.encode(user.getWachtwoord());
        user.setWachtwoord(hashedWachtwoord);
        userRepository.save(user);
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

    public void logoutUser(){
        userSession.logoutSession();
    }
}
