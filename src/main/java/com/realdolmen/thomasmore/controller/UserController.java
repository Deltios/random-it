package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.Klant;
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
    private Klant nieuweKlant = new Klant();
    private String newUserVoornaam;
    private String newUserFamilienaam;
    private String newPaswoord;
    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    public void createUser() {
        userService.createUser(newUserVoornaam, newUserFamilienaam, newPaswoord);
        addMessage("User toegevoegd!");
        clearForm();
    }
    public String showUserDetails(){
        return "details";
    }
    private void clearForm() {
        newUserVoornaam = null;
        newUserFamilienaam = null;
        newPaswoord = null;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public String registerKlant(){
        userService.registerKlant(nieuweKlant);
        return "details";
    }
    public String loginKlant(String email, String wachtwoord){
        boolean juisteLogin = userService.checkLogin(email, wachtwoord);
        if (juisteLogin){
            nieuweKlant = (Klant)userService.getByEmail(email);
            return "details";
        }
        else{
            return "verkeerdeLogin";
        }
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

    public void setNewPaswoord(String newPaswoord) {
        this.newPaswoord = newPaswoord;
    }

    public Klant getNieuweKlant() {
        return nieuweKlant;
    }

    public void setNieuweKlant(Klant nieuweKlant) {
        this.nieuweKlant = nieuweKlant;
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
