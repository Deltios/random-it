package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.Klant;
import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.service.UserService;
import com.realdolmen.thomasmore.session.UserSession;

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

    private Klant huidigeKlant = new Klant();
    private String newUserVoornaam;
    private String newUserFamilienaam;
    private String newAdres;
    private String newGemeente;
    private String newPostcode;
    private String newEmail;
    private String newPaswoord;
    private String newTelefoon;

    private Long sessionUserId;
    private String errorMessage;

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

    public String registerKlant(){
        if (huidigeKlant == null){
            errorHandling("Er zijn geen gegevens ingestuurd.");
        }
        userService.registerKlant(huidigeKlant);
        return "details";
    }

    public String loginKlant(){
        User user = userService.authenticateUser(newEmail, newPaswoord);
        if (user !=  null){
            huidigeKlant = (Klant)userService.getByEmail(newEmail);
            Long id =userService.setUserSession(user);
            this.sessionUserId = id;
            this.newEmail = null;
            this.newPaswoord = null;
            return "details";
        }
        else{
            errorHandling("Er is geen gebruiker  ingelogd.");
        }
        return "index";
    }
    public String logoutUser(){
        if  (this.sessionUserId==null){
            errorHandling("Er is geen gebruiker  ingelogd.");
        }
        userService.logoutUser();
        this.sessionUserId =  null;
        this.huidigeKlant=null;
        return "loggedOut";
    }
    public String errorHandling(String errorMessage){
        this.errorMessage= errorMessage;
        return "errorPage";
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

    public void setNewPaswoord(String newPaswoord) {
        this.newPaswoord = newPaswoord;
    }

    public Klant getHuidigeKlant() {
        return huidigeKlant;
    }

    public void setHuidigeKlant(Klant huidigeKlant) {
        this.huidigeKlant = huidigeKlant;
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

    public Long getSessionUserId() {
        return sessionUserId;
    }

    public void setSessionUserId(Long sessionUserId) {
        this.sessionUserId = sessionUserId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
