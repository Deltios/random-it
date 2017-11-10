package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.SupportTicket;
import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.service.SupportTicketService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@ManagedBean
@SessionScoped
public class SupportTicketController {
    @ManagedProperty("#{supportTicketService}")
    private SupportTicketService supportTicketService;

    private SupportTicket supportTicket = new SupportTicket();

    public SupportTicket getSupportTicket() {
        return supportTicket;
    }

    public void setSupportTicket(SupportTicket supportTicket){
        this.supportTicket = supportTicket;
    }

    public void add(){
        this.supportTicketService.createSupportTicket(Calendar.getInstance(),supportTicket.getNaam(),supportTicket.getOpmerking());
        this.supportTicket = new SupportTicket();
    }


    private User newUser;
    private Calendar newDatumAanvraag;
    private String newOpmerking;
    private String newNaam;

    public List<SupportTicket> getSupportTickets() {
        return supportTicketService.findAllSupportTickets();
    }

    public void createSupportTicket() {
        supportTicketService.createSupportTicket(newDatumAanvraag, newOpmerking, newNaam);
        addMessage("Support ticket toegevoegd!");
        clearForm();
    }

    public void createTestSupportTickets(){
        supportTicketService.createSupportTicket(new GregorianCalendar(2017, 10, 11 ), "Geen", "mrappel");
        supportTicketService.createSupportTicket(new GregorianCalendar(2017, 9, 3 ), "Geen", "mrPeer");
        supportTicketService.createSupportTicket(new GregorianCalendar(2017, 5, 15 ), "Geen", "mrBanaan");
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
