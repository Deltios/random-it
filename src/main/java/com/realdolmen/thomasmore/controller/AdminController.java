package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.service.UserService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AdminController {
    @ManagedProperty("#{userService}")
    private UserService userService;
    private User user;

    public String login(User admin){
        if (admin.getUserLevel() != 3){
            return "noAccess";
        }
        return "adminMain";
    }
    public String editUsers(){
        return ("editUsers");
    }
    public String editProducts(){
        return "editProducts";
    }
    public String editSingleUser(){
        return "editSingleUser";
    }
    public String editProductDetails(){
        return "editProductDetails";
    }
    public String editMerken() {
        return "editMerken";
    }
    public String editCategorieen(){
        return "editcategorieen";
    }



    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
