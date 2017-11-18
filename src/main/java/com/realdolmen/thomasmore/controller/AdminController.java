package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.*;
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

    private List<Bestelling> bestellingen;
    private User user;
    private Product product;

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

    //List retrieval
    public List<User> getAllUsers(){
        return adminService.findAllUsers();
    }
    public String getAllBestellingen(){
        bestellingen = adminService.findAllBestelling();
        return "bestellingTable";
    }
    public List<Product> getAllProducts(){
        return adminService.findAllProducts();
    }
    public List<Merk> getAllMerken(){ return adminService.findAllMerken(); }
    public List<Categorie> getAllCategorieen(){return adminService.findAllCategorie(); }
    //CRUD
    public String getBestellingenPersoon(long id){
        bestellingen = adminService.findBestellingenByPerson(id);
        return "bestellingTable";
    }
    public String newUser(){
        user = new User();
        return "edit/editUser";
    }
    public String editUsers(Long id){
        user = userService.getUser(id);
        return ("edit/editUser");
    }


    public String editOrSaveSingleUser(){
        adminService.saveOrUpdateUser(user);
        user = null;
        return "../userTable.xhtml";
    }
    public String editProducts(long id){
        product = adminService.findProductById(id);
        return "edit/editProduct";
    }

    public String editProductDetails(){
        adminService.saveOrUpdateProduct(product);
        return "editProductDetails";
    }
    public String editMerken() {
        return "editMerken";
    }
    public String editCategorieen(){
        return "editcategorieen";
    }

    public void deleteUser(long id) {
        adminService.deleteUser(id);
    }
    public void deleteProduct(long id){ adminService.deleteProduct(id);}
    public void deleteMerk(long id){ adminService.deleteProduct(id);}
    public void deleteCategorie(long id){ adminService.deleteProduct(id);}
    public void deleteSpecificatie(long id){ adminService.deleteProduct(id);}
    public void deleteBestelling(long id){ adminService.deleteProduct(id);}

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

    public void setBestellingen(List<Bestelling> bestellingen) {
        this.bestellingen = bestellingen;
    }
    public List<Bestelling> getBestellingen(){ return this.bestellingen;}

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
