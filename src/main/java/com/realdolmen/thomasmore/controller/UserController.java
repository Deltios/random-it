package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@ViewScoped
public class UserController {
    @ManagedProperty("#{userService}")
    private UserService userService;

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

    private void clearForm() {
        newUserVoornaam = null;
        newUserFamilienaam = null;
        newPaswoord = null;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
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

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
