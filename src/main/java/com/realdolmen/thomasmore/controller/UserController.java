package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserController {
    @ManagedProperty("#{userService}")
    private UserService userService;
    private User newUser = new User();
    private String newUserVoornaam;
    private String newUserFamilienaam;
    private String newAdres;
    private String newGemeente;
    private String newPostcode;
    private String newEmail;
    private String newPaswoord;
    private String newTelefoon;

    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    /*
    public void createUser(){
        userService.createUser(newUserVoornaam, newUserFamilienaam, newAdres, newGemeente, newPostcode, newEmail, newPaswoord, newTelefoon);
        addMessage("User toegevoegd!");
        clearForm();
    }*/

    public void createTestUsers(){
        userService.createUser("Jan","Peeters","Bergenstraat 12","Dessel",
                "2480","jan.peeters@gmail.com","Geheim","0475913475");
        userService.createUser("Els","Vandenbroecke","Molenberg 5","Kasterlee",
                "2460","els.vdbroecke@gmail.com","Abc123","0494327496");
        userService.createUser("Dirk","Janssens","Ooststraat 15","Arendonk",
                "2370","dirk.janssens@gmail.com","Tgdk","0461783519");
        addMessage("Test users toegevoegd!");
    }

    public String showUserDetails(){
        return "details";
    }
    private void clearForm() {
        newUserVoornaam = null;
        newUserFamilienaam = null;
        newAdres = null;
        newGemeente = null;
        newPostcode = null;
        newEmail = null;
        newPaswoord = null;
        newTelefoon = null;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void test(){
        addMessage("Test users toegevoegd!");
    }

    public String getNewUserVoornaam() {
        return newUserVoornaam;
    }

    public void setNewUserVoornaam(String newUserVoornaam) {
        this.newUserVoornaam = newUserVoornaam;
    }

    public String getNewUserFamilienaam() {
        return newUserFamilienaam;
    }

    public void setNewUserFamilienaam(String newUserFamilienaam) {
        this.newUserFamilienaam = newUserFamilienaam;
    }

    public String getNewPaswoord() {
        return newPaswoord;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public void setNewPaswoord(String newPaswoord) {
        this.newPaswoord = newPaswoord;
    }

    public String getNewAdres() {
        return newAdres;
    }

    public void setNewAdres(String newAdres) {
        this.newAdres = newAdres;
    }

    public String getNewGemeente() {
        return newGemeente;
    }

    public void setNewGemeente(String newGemeente) {
        this.newGemeente = newGemeente;
    }

    public String getNewPostcode() {
        return newPostcode;
    }

    public void setNewPostcode(String newPostcode) {
        this.newPostcode = newPostcode;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewTelefoon() {
        return newTelefoon;
    }

    public void setNewTelefoon(String newTelefoon) {
        this.newTelefoon = newTelefoon;
    }

    public UserService getUserService() {
        return userService;
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
