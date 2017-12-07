package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.*;
import com.realdolmen.thomasmore.service.AdminService;
import com.realdolmen.thomasmore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;
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

    public SupportTicket SupportTicketByOnderwerp;
    public String SupportByName;
    private List<Bestelling> bestellingen;
    private User user;
    private Product product;
    private Merk merk;
    private Categorie categorie;


    private String newPassword = null;
    public AdminController() {
    }

    public String getSupportByName() {
        return SupportByName;
    }

    public void setSupportByName(String supportByName) {
        SupportByName = supportByName;
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

    public String editSupport(String onderwerp){
        SupportTicketByOnderwerp = adminService.findSupportByOnderwerpName(onderwerp);
       // SupportByName = adminService.findSupportByOnderwerpName(onderwerp);
        return ("edit/editSupport");
    }

    public SupportTicket getSupportTicketByOnderwerp() {
        return SupportTicketByOnderwerp;
    }

    public void setSupportTicketByOnderwerp(SupportTicket supportTicketByOnderwerp) {
        SupportTicketByOnderwerp = supportTicketByOnderwerp;
    }

    public String newUser(){
        user = new User();
        this.newPassword="";
        return "edit/editUser";
    }
    public String editUsers(Long id){
        user = userService.getUser(id);
        this.newPassword="";
        return ("edit/editUser");
    }
    public void saveUser(){
        this.user.setWachtwoord(this.newPassword);
        adminService.saveOrUpdateUser(user);
        user = null;
        this.redirectToPage("../userTable.xhtml");
    }
    public void deleteUser(long id) {
        adminService.deleteUser(id);
    }

    public String newProduct(){
        product = new Product();
        return "edit/editProduct";
    }
    public String editProducts(long id){
        product = adminService.findProductById(id);
        return "edit/editProduct";
    }
    public void saveProduct(){
        adminService.saveOrUpdateProduct(product);
        this.redirectToPage("../productsTable.xhtml");
    }
    public void deleteProduct(long id){ adminService.deleteProduct(id);}

    public String newMerk(){
        merk = new Merk();
        return "edit/editMerk";
    }
    public String editMerken(long id) {
        merk = adminService.findMerkById(id);
        return "edit/editMerk";
    }
    public void saveMerk(){
        adminService.saveOrUpdateMerk(merk);
        this.getAllMerken();
        this.redirectToPage("../merkenTable.xhtml");

    }
    public void deleteMerk(long id){ adminService.deleteMerk(id);}

    public String newCategorie(){
        categorie = new Categorie();
        return "edit/editCategorie";
    }
    public String editCategorieen(Long id){
        categorie = adminService.findCategorieById(id);
        return "edit/editCategorie";
    }
    public void saveCategorie(){
        adminService.saveOrUpdateCategorie(categorie);
        this.redirectToPage("../categorieTable.xhtml");
    }
    public void deleteCategorie(long id){ adminService.deleteCategorie(id);}

    public void deleteBestelling(long id){
        adminService.deleteBestelling(id);
    }


    public void deleteSpecificatie(long id){ adminService.deleteSpecificatie(id);}

    public void deleteSupportTicket(long id){ adminService.deleteBestelling(id);}


    private void redirectToPage(String s){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        try {
            ec.redirect(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Getters and setters
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

    public Merk getMerk() {
        return merk;
    }
    public void setMerk(Merk merk) {
        this.merk = merk;
    }

    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }


}
