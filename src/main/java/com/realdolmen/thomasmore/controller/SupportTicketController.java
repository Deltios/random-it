package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.SupportTicket;
import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.service.AdminService;
import com.realdolmen.thomasmore.service.SupportTicketService;
import com.realdolmen.thomasmore.service.UserService;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import com.realdolmen.thomasmore.service.UserService;
import com.realdolmen.thomasmore.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean
@SessionScoped
public class SupportTicketController {
    @Autowired
    private HttpSession session;

    @ManagedProperty("#{supportTicketService}")
    private SupportTicketService supportTicketService;

    private SupportTicket supportTicket = new SupportTicket();

    public SupportTicket getSupportTicket() {
        return supportTicket;
    }


    public String naarsupport() {
        return "nieuwspupportticket";
    }

    public void setSupportTicket(SupportTicket supportTicket){
        this.supportTicket = supportTicket;
    }

    public void add(HttpSession session){
        User user = (User)session.getAttribute("user");
        this.supportTicketService.createSupportTicket(Calendar.getInstance(),supportTicket.getNaam(),supportTicket.getOpmerking(), supportTicket.getOnderwerp(),user);
        this.supportTicket = new SupportTicket();
    }

    private User newUser;
    private Calendar newDatumAanvraag;
    private String newOpmerking;
    private String newNaam;
    private String newOnderwerp;

    public List<SupportTicket> getSupportTickets() {
        return supportTicketService.findAllSupportTickets();
    }

    public void createSupportTicket() {
        supportTicketService.createSupportTicket(newDatumAanvraag, newOpmerking, newNaam, newOnderwerp, newUser);
        addMessage("Support ticket toegevoegd!");
        clearForm();
    }

    public List<User> getAllCategorieenid(){
        return supportTicketService.findAllCategorieid();
    }

    public void createTestSupportTickets(){
     //   supportTicketService.createSupportTicket(LocalDate.of(2017, 10, 11 ), "Geen", "mrappel");
     //   supportTicketService.createSupportTicket(LocalDate.of(2017, 9, 3 ), "Geen", "mrPeer");
     //   supportTicketService.createSupportTicket(LocalDate.of(2017, 5, 15 ), "Geen", "mrBanaan");
    }

    private void clearForm() {
        newUser = null;
        newDatumAanvraag = null;
        newOpmerking = null;
        newNaam = null;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Date now() {
        return new Date();
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setSupportTicketService(SupportTicketService supportTicketService) {
        this.supportTicketService = supportTicketService;
    }

}
