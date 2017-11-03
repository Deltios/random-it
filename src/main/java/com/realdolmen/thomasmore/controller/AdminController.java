package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.Bestelling;
import com.realdolmen.thomasmore.data.Product;
import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.service.AdminService;
import com.realdolmen.thomasmore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import java.util.List;

@ManagedBean
@SessionScoped
public class AdminController {
    @Autowired
    private HttpSession session;

    @ManagedProperty("#{userService}")
    private UserService userService;

    @ManagedProperty("#{adminService}")
    private AdminService adminService;
    private User user;

    public AdminController() {
    }

    public AdminController(HttpSession session, UserService userService ) {
        this.userService = userService;
        this.user = (User) session.getAttribute("user");

    }
    public String login(User admin){
        if (admin.getUserLevel() != 3){
            return "noAccess";
        }
        return "adminMain";
    }
    public boolean isAdmin(Long userId){
        User user = userService.getUser(userId);
        return user.getUserLevel() == 3;
    }
    public List<User> getAllUsers(){
        return adminService.findAllUsers();
    }
    public List<Bestelling> getBestellingen(Long id){
        return adminService.findAllBestelling();
    }
    public List<Product> getAllProducts(){
        return adminService.findAllProducts();
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

    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String testSession(HttpSession session) {
        this.user = (User) session.getAttribute("user");
        return "TestSession";
    }
}
