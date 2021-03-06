package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.BesteldProduct;
import com.realdolmen.thomasmore.data.Bestelling;
import com.realdolmen.thomasmore.data.Klant;
import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.service.UserService;
import com.realdolmen.thomasmore.session.UserSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.primefaces.component.calendar.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.GregorianCalendar;
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
    private int newUserLevel;
    private int sessionUserLevel;

    private Long sessionUserId;
    private String errorMessage;
    private User selectedUser;

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
        userService.createKlant("Jan","Peeters","Bergenstraat 12","Dessel",
                "2480","jan.peeters@gmail.com","$2a$10$QDVMASSRtkFnrPN4lL26zuldrFwnCIXPJp/8HuU6.tnEsT4lGvwWW",
                "0475913475", 3, new GregorianCalendar());
        userService.createKlant("Els","Vandenbroecke","Molenberg 5","Kasterlee",
                "2460","els.vdbroecke@gmail.com","$2a$10$0ZMOsytIfXCywOP2VGRiv.9YfiYqrZshzsF4/OuWRSqCagskJZIfm",
                "0494327496", 1, new GregorianCalendar());
        userService.createKlant("Dirk","Janssens","Ooststraat 15","Arendonk",
                "2370","dirk.janssens@gmail.com","$2a$10$ovH6rL2V5VJw9V7ANc3MeuRBNBAp/fwfJi/gFvqkYzzeXg8oqtrkW",
                "0461783519", 1, new GregorianCalendar());
        userService.createKlant("Andrew","Johnson","Schoolstraat 19","Oostende",
                "8400","andrew.johnson@gmail.com","$2a$10$sWiF4f26d8QRgubo4kdt0e7QlOoudncqtMkRwERHeM89tVxl05joq",
                "0463516483", 1, new GregorianCalendar());
        userService.createKlant("Eline","Vangeel","Heikant 56","Oostende",
                "8400","eline.vangeel@gmail.com","$2a$10$DESFNSA2v0721eWodSsFouFp0b4/c47QZmrAo.tBPK5.a57WWHGsS",
                "0498213678", 1, new GregorianCalendar());

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
        newUserLevel = 0;
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

    public String loginKlant(HttpSession session){
        User user = userService.authenticateUser(newEmail, newPaswoord);
        if (user !=  null){
            huidigeKlant = (Klant)userService.getByEmail(newEmail);
            session.setAttribute("user", user);
            this.sessionUserId = user.getId();
            this.sessionUserLevel = user.getUserLevel();
            this.newEmail = null;
            this.newPaswoord = null;
            this.selectedUser = user;
            if (user.getUserLevel() == 3){
                return "/admin/adminMain";
            }
            return "/user/my-account/dashboard";
        }
        else{
            return errorHandling("Je inloggegevens zijn niet juist.");
        }
    }
    public String logoutUser(HttpSession session){
        if  (this.sessionUserId == null){
            return errorHandling("Er is geen gebruiker  ingelogd.");
        }
        userService.logoutUser();
        this.sessionUserId = null;
        this.huidigeKlant = null;
        this.selectedUser=null;
        session.setAttribute("user", null);
        session.setAttribute("winkelkarretje", null);
        return "/index";
    }
    public List<Bestelling> GetHuidigeBestellingen(){
        List<Bestelling> bestellingen = selectedUser.getBestellingen();
        return bestellingen;
    }
    public Bestelling GetLatestBestelling(){
        return selectedUser.getBestellingen().get(selectedUser.getBestellingen().size()-1);
    }
    public void saveUserData(){
        this.selectedUser.setWachtwoord(this.newPaswoord);
        this.newPaswoord = null;
        userService.updateUser(this.selectedUser);
    }
    public String errorHandling(String errorMessage){
        this.errorMessage= errorMessage;
        return "errorPage";
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

    public int getNewUserLevel() {
        return newUserLevel;
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

    public Long getSessionUserId() {
        return sessionUserId;
    }

    public void setSessionUserId(Long sessionUserId) {
        this.sessionUserId = sessionUserId;
    }

    public int getSessionUserLevel() {
        return sessionUserLevel;
    }

    public void setSessionUserLevel(int sessionUserLevel) {
        this.sessionUserLevel = sessionUserLevel;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}
