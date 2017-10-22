package com.realdolmen.thomasmore.controller;

import com.realdolmen.thomasmore.data.SupportTicket;
import com.realdolmen.thomasmore.data.User;
import com.realdolmen.thomasmore.service.SupportTicketService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@ManagedBean
@ViewScoped
public class SupportTicketController {
    @ManagedProperty("#{supportTicketService}")
    private SupportTicketService supportTicketService;

    private User newUser;
    private Calendar newDatumAanvraag;
    private String newOpmerking;

    public List<SupportTicket> getSupportTickets() {
        return supportTicketService.findAllSupportTickets();
    }

    public void createSupportTicket() {
        supportTicketService.createSupportTicket(newDatumAanvraag, newOpmerking);
        addMessage("Support ticket toegevoegd!");
        clearForm();
    }

    public void createTestSupportTickets(){
        supportTicketService.createSupportTicket(new GregorianCalendar(2017, 10, 11 ), "Geen");
        supportTicketService.createSupportTicket(new GregorianCalendar(2017, 9, 3 ), "Geen");
        supportTicketService.createSupportTicket(new GregorianCalendar(2017, 5, 15 ), "Geen");
    }

    private void clearForm() {
        newUser = null;
        newDatumAanvraag = null;
        newOpmerking = null;
    }

    private void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Deze setter MOET aanwezig zijn, anders kan spring deze service niet injecteren.
     */
    public void setSupportTicketService(SupportTicketService supportTicketService) {
        this.supportTicketService = supportTicketService;
    }
}
