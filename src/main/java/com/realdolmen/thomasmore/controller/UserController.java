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

    private String newUserUsername;
    private String newUserPassword;
    private int newUserLevel;

    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    public void createUser() {
        userService.createUser(newUserUsername, newUserPassword, newUserLevel);
        addMessage("User toegevoegd!");
        clearForm();
    }

    private void clearForm() {
        newUserUsername = null;
        newUserPassword = null;
        newUserLevel = 0;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getNewUserUsername() {
        return newUserUsername;
    }

    public String getNewUserPassword() {
        return newUserPassword;
    }

    public int getNewUserLevel() {
        return newUserLevel;
    }

    public void setNewUserUsername(String newEmployeeFirstName) {
        this.newUserUsername = newEmployeeFirstName;
    }

    public void setNewUserPassword(String newEmployeeLastName) {
        this.newUserPassword = newEmployeeLastName;
    }

    public void setNewUserLevel(int newUserLevel) {
        this.newUserLevel = newUserLevel;
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
