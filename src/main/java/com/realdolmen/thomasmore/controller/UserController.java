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

    public List<User> getUsers() {
        return userService.findAllUsers();
    }

    /*public void createUser() {
        userService.createUser(newUserUsername, newUserPassword);
        addMessage("User toegevoegd!");
        clearForm();
    }*/

    private void clearForm() {
        newUserUsername = null;
        newUserPassword = null;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getNewEmployeeFirstName() {
        return newUserUsername;
    }

    public String getNewEmployeeLastName() {
        return newUserPassword;
    }

    public void setNewEmployeeFirstName(String newEmployeeFirstName) {
        this.newUserUsername = newEmployeeFirstName;
    }

    public void setNewEmployeeLastName(String newEmployeeLastName) {
        this.newUserPassword = newEmployeeLastName;
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setEmployeeService(UserService userService) {
        this.userService = userService;
    }
}
